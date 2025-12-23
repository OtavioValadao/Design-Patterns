# 🎮 Command Pattern - Guia de Implementação

## 📖 O que é o Command Pattern?

O **Command Pattern** é um padrão comportamental que **transforma solicitações em objetos**. Em vez de executar uma ação diretamente, você encapsula a ação em um objeto que pode ser armazenado, passado como parâmetro, enfileirado e principalmente **desfeito (undo)**.

### 🎯 Analogia Simples
Pense em um **controle remoto**: cada botão cria um comando que pode ser executado, armazenado no histórico e até desfeito (como voltar o canal anterior).

### 🤔 Quando Usar?
- ✅ Precisa de **Undo/Redo** (desfazer e refazer operações)
- ✅ Quer manter **histórico** de ações executadas
- ✅ Precisa **agendar** operações para executar depois
- ✅ Quer implementar **transações** (várias operações como uma)
- ✅ Precisa de **auditoria** de comandos executados

---

## 📁 Estrutura de Arquivos a Criar

```
src/com/patterns/stocktrading/
└── command/
    ├── CommandOperacao.java           # Interface base
    ├── CompraCommand.java             # Comando de compra
    ├── VendaCommand.java              # Comando de venda
    ├── RelatorioCommand.java          # Comando de relatório
    ├── MacroCommand.java              # Comando composto (opcional)
    └── HistoricoComandos.java         # Gerenciador de histórico
```

---

## 🚀 Implementação Passo a Passo

### Passo 1: Criar a Interface Command

**Arquivo:** `src/com/patterns/stocktrading/command/CommandOperacao.java`

```java
package com.patterns.stocktrading.command;

import java.time.LocalDateTime;

public interface CommandOperacao {
    void executar();
    void desfazer();
    String getDescricao();
    LocalDateTime getTimestamp();
    boolean foiExecutado();
}
```

### Passo 2: Criar Comando Concreto (Exemplo: Compra)

**Arquivo:** `src/com/patterns/stocktrading/command/CompraCommand.java`

```java
package com.patterns.stocktrading.command;

import com.patterns.stocktrading.model.Carteira;
import com.patterns.stocktrading.model.Ordem;
import com.patterns.stocktrading.service.OperacaoService;
import java.time.LocalDateTime;

public class CompraCommand implements CommandOperacao {
    private final Ordem ordem;
    private final Carteira carteira;
    private final OperacaoService service;
    private final LocalDateTime timestamp;
    private boolean executado = false;
    
    public CompraCommand(Ordem ordem, Carteira carteira, OperacaoService service) {
        this.ordem = ordem;
        this.carteira = carteira;
        this.service = service;
        this.timestamp = LocalDateTime.now();
    }
    
    @Override
    public void executar() {
        if (!executado) {
            System.out.println("🔵 Executando: " + getDescricao());
            service.executarOperacao(ordem, carteira);
            executado = true;
        }
    }
    
    @Override
    public void desfazer() {
        if (executado) {
            System.out.println("🔄 Desfazendo: " + getDescricao());
            // Cria ordem reversa (venda)
            Ordem reversa = new Ordem(
                "VENDA", ordem.getTipoAcao(), ordem.getCodigo(),
                ordem.getQuantidade(), ordem.getPrecoLimite(),
                ordem.getBolsa(), ordem.getOrigem()
            );
            service.executarOperacao(reversa, carteira);
            executado = false;
        }
    }
    
    @Override
    public String getDescricao() {
        return String.format("COMPRA %d %s @ R$ %.2f", 
            ordem.getQuantidade(), ordem.getCodigo(), ordem.getPrecoLimite());
    }
    
    @Override
    public LocalDateTime getTimestamp() { return timestamp; }
    
    @Override
    public boolean foiExecutado() { return executado; }
}
```

### Passo 3: Criar o Gerenciador de Histórico

**Arquivo:** `src/com/patterns/stocktrading/command/HistoricoComandos.java`

```java
package com.patterns.stocktrading.command;

import java.util.Stack;

public class HistoricoComandos {
    private final Stack<CommandOperacao> comandosExecutados;
    private final Stack<CommandOperacao> comandosDesfeitos;
    
    public HistoricoComandos() {
        this.comandosExecutados = new Stack<>();
        this.comandosDesfeitos = new Stack<>();
    }
    
    // Executa comando e adiciona ao histórico
    public void executarComando(CommandOperacao comando) {
        comando.executar();
        if (comando.foiExecutado()) {
            comandosExecutados.push(comando);
            comandosDesfeitos.clear(); // Limpa redo ao executar novo
        }
    }
    
    // Desfaz último comando
    public void desfazer() {
        if (!comandosExecutados.isEmpty()) {
            CommandOperacao comando = comandosExecutados.pop();
            comando.desfazer();
            comandosDesfeitos.push(comando);
        } else {
            System.out.println("⚠️ Nenhum comando para desfazer");
        }
    }
    
    // Refaz último comando desfeito
    public void refazer() {
        if (!comandosDesfeitos.isEmpty()) {
            CommandOperacao comando = comandosDesfeitos.pop();
            comando.executar();
            comandosExecutados.push(comando);
        } else {
            System.out.println("⚠️ Nenhum comando para refazer");
        }
    }
    
    // Exibe histórico
    public void exibirHistorico() {
        System.out.println("\n📜 HISTÓRICO DE COMANDOS");
        System.out.println("=".repeat(60));
        if (comandosExecutados.isEmpty()) {
            System.out.println("   (vazio)");
        } else {
            for (int i = 0; i < comandosExecutados.size(); i++) {
                CommandOperacao cmd = comandosExecutados.get(i);
                System.out.printf("   %d. %s%n", i + 1, cmd.getDescricao());
            }
        }
        System.out.println("=".repeat(60) + "\n");
    }
    
    public int getTamanhoHistorico() {
        return comandosExecutados.size();
    }
}
```

### Passo 4: Usar na Aplicação

**Modificar:** `BolsaAplicacao.java`

```java
public void demonstrarCommandPattern() {
    HistoricoComandos historico = new HistoricoComandos();
    OperacaoService service = new OperacaoService();
    Carteira carteira = new Carteira("Minha Carteira");
    
    // Criar comandos
    Ordem ordem1 = new OrdemBuilder()
        .paraCompra()
        .comCodigo("PETR4")
        .comQuantidade(100)
        .comPrecoLimite(30.0)
        .construir();
    
    CommandOperacao compra = new CompraCommand(ordem1, carteira, service);
    
    // Executar
    historico.executarComando(compra);
    
    // Exibir histórico
    historico.exibirHistorico();
    
    // Ops, erro! Desfazer
    historico.desfazer();
    
    // Mudou de ideia? Refazer
    historico.refazer();
}
```

---

## 🎨 Diagrama do Padrão

```
┌─────────────────┐
│    Cliente      │  (cria e solicita comando)
│ BolsaAplicacao  │
└────────┬────────┘
         │ cria
         ↓
┌─────────────────┐
│  Command        │  (interface)
│ + executar()    │
│ + desfazer()    │
└────────┬────────┘
         │ implementa
         ↓
┌─────────────────┐
│ CompraCommand   │  (comando concreto)
│ VendaCommand    │
└────────┬────────┘
         │ usa
         ↓
┌─────────────────┐
│   Receiver      │  (quem faz a ação de verdade)
│ OperacaoService │
└─────────────────┘
         ↑
         │ gerencia
┌─────────────────┐
│   Histórico     │  (invoker - gerencia comandos)
└─────────────────┘
```

---

## ✅ Benefícios

| Benefício | Descrição |
|-----------|-----------|
| **Undo/Redo** | Desfazer e refazer operações facilmente |
| **Histórico** | Manter registro de todas as ações |
| **Desacoplamento** | Cliente não precisa saber como executar |
| **Fila** | Agendar comandos para executar depois |
| **Transações** | Executar múltiplos comandos como um |
| **Auditoria** | Log completo de todas as operações |

---

## 📝 Checklist de Implementação

- [ ] Criar pasta `command/`
- [ ] Criar interface `CommandOperacao.java`
- [ ] Implementar `CompraCommand.java`
- [ ] Implementar `VendaCommand.java`
- [ ] Implementar `RelatorioCommand.java`
- [ ] Criar `HistoricoComandos.java`
- [ ] Integrar com `BolsaAplicacao.java`
- [ ] Testar undo/redo
- [ ] Testar histórico
- [ ] Compilar e executar

---

## 🔧 Comandos para Compilar e Executar

```bash
# Compilar
javac -d out src/com/patterns/stocktrading/**/*.java

# Executar
java -cp out com.patterns.stocktrading.BolsaAplicacao
```

---

## 💡 Dicas Importantes

1. **Reversibilidade**: Nem todo comando precisa ser reversível (ex: relatórios)
2. **Estado**: Guarde informações necessárias para desfazer
3. **Validação**: Valide antes de executar
4. **Limite**: Considere limitar tamanho do histórico
5. **Serialização**: Commands podem ser salvos em disco para persistência

---

## 🆚 Quando NÃO usar

- ❌ Operações simples e diretas (overhead desnecessário)
- ❌ Não precisa de histórico ou undo
- ❌ Operações não podem ser revertidas por natureza

---

## 📚 Padrões Relacionados

- **Memento**: Para salvar estado ao invés de reverter ação
- **Chain of Responsibility**: Para processar comandos em cadeia
- **Composite**: Para MacroCommands (comandos compostos)

---

**Padrão:** Comportamental  
**Complexidade:** ⭐⭐⭐ (Média)  
**Uso no Projeto:** Controle de operações de compra/venda com histórico e undo/redo

