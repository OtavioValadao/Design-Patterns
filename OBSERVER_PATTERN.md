# 👁️ Observer Pattern - Guia de Implementação

## 📖 O que é o Observer Pattern?

O **Observer Pattern** é um padrão comportamental que define uma **dependência um-para-muitos** entre objetos. Quando um objeto muda de estado, todos os seus dependentes são **notificados e atualizados automaticamente**.

### 🎯 Analogia Simples
Pense em uma **inscrição no YouTube**: quando um canal publica um vídeo novo, todos os inscritos recebem notificação automaticamente. Os inscritos são os **Observers** e o canal é o **Subject**.

### 🤔 Quando Usar?
- ✅ Precisa de **notificações automáticas** quando algo muda
- ✅ Quer sistema **reativo** (push notifications)
- ✅ Múltiplos objetos dependem do estado de um
- ✅ Precisa de **desacoplamento** entre notificador e notificados
- ✅ Quer adicionar/remover observadores em **runtime**

---

## 📁 Estrutura de Arquivos a Criar

```
src/com/patterns/stocktrading/
└── observer/
    ├── PrecoObserver.java                # Interface observer
    ├── PrecoSubject.java                 # Interface subject
    ├── EmailNotificacaoObserver.java     # Observer de email
    ├── SmsNotificacaoObserver.java       # Observer de SMS
    ├── PushNotificacaoObserver.java      # Observer de push
    └── LogNotificacaoObserver.java       # Observer de log (opcional)

Modificar:
└── service/
    └── CotacaoService.java               # Transformar em Subject
```

---

## 🚀 Implementação Passo a Passo

### Passo 1: Criar a Interface Observer

**Arquivo:** `src/com/patterns/stocktrading/observer/PrecoObserver.java`

```java
package com.patterns.stocktrading.observer;

import com.patterns.stocktrading.factorymethod.Acao;

public interface PrecoObserver {
    /**
     * Método chamado quando o preço muda
     */
    void atualizar(Acao acao, double precoAnterior, 
                   double precoAtual, double variacao);
    
    String getNome();
    double getLimiteVariacao();
}
```

### Passo 2: Criar a Interface Subject

**Arquivo:** `src/com/patterns/stocktrading/observer/PrecoSubject.java`

```java
package com.patterns.stocktrading.observer;

import com.patterns.stocktrading.factorymethod.Acao;

public interface PrecoSubject {
    void registrarObserver(PrecoObserver observer);
    void removerObserver(PrecoObserver observer);
    void notificarObservers(Acao acao, double precoAnt, double precoAtual);
    int contarObservers();
}
```

### Passo 3: Criar Observer Concreto (Exemplo: Email)

**Arquivo:** `src/com/patterns/stocktrading/observer/EmailNotificacaoObserver.java`

```java
package com.patterns.stocktrading.observer;

import com.patterns.stocktrading.factorymethod.Acao;
import com.patterns.stocktrading.util.FormatoUtil;

public class EmailNotificacaoObserver implements PrecoObserver {
    private final String emailDestinatario;
    private final double limiteVariacao;
    
    public EmailNotificacaoObserver(String email, double limite) {
        this.emailDestinatario = email;
        this.limiteVariacao = limite;
    }
    
    @Override
    public void atualizar(Acao acao, double precoAnt, 
                         double precoAtual, double variacao) {
        // Só notifica se ultrapassar o limite
        if (Math.abs(variacao) >= limiteVariacao) {
            enviarEmail(acao, precoAnt, precoAtual, variacao);
        }
    }
    
    private void enviarEmail(Acao acao, double precoAnt, 
                            double precoAtual, double variacao) {
        System.out.println("\n📧 ═══════════════════════════════════════");
        System.out.println("   NOTIFICAÇÃO POR EMAIL");
        System.out.println("   Para: " + emailDestinatario);
        System.out.println("   ───────────────────────────────────────");
        System.out.println("   Ação: " + acao.getCodigo());
        System.out.println("   Preço Anterior: R$ " + 
                         String.format("%.2f", precoAnt));
        System.out.println("   Preço Atual: R$ " + 
                         String.format("%.2f", precoAtual));
        System.out.println("   Variação: " + 
                         FormatoUtil.formatarValor(variacao, "PORCENTAGEM"));
        System.out.println("   Status: " + (variacao > 0 ? "📈 ALTA" : "📉 QUEDA"));
        System.out.println("   ═══════════════════════════════════════\n");
    }
    
    @Override
    public String getNome() {
        return "EmailObserver(" + emailDestinatario + ")";
    }
    
    @Override
    public double getLimiteVariacao() {
        return limiteVariacao;
    }
}
```

### Passo 4: Transformar CotacaoService em Subject

**Modificar:** `src/com/patterns/stocktrading/service/CotacaoService.java`

```java
package com.patterns.stocktrading.service;

import com.patterns.stocktrading.factorymethod.Acao;
import com.patterns.stocktrading.model.Cotacao;
import com.patterns.stocktrading.observer.PrecoObserver;
import com.patterns.stocktrading.observer.PrecoSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CotacaoService implements PrecoSubject {
    // Lista de observers registrados
    private final List<PrecoObserver> observers;
    
    // Cache de preços anteriores
    private final Map<String, Double> precosAnteriores;
    
    public CotacaoService() {
        this.observers = new ArrayList<>();
        this.precosAnteriores = new HashMap<>();
    }
    
    // ========================================
    // Implementação do PrecoSubject
    // ========================================
    
    @Override
    public void registrarObserver(PrecoObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✅ Observer registrado: " + observer.getNome());
        }
    }
    
    @Override
    public void removerObserver(PrecoObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("❌ Observer removido: " + observer.getNome());
        }
    }
    
    @Override
    public void notificarObservers(Acao acao, double precoAnt, double precoAtual) {
        double variacao = 0.0;
        if (precoAnt > 0.0) {
            variacao = (precoAtual - precoAnt) / precoAnt;
        }
        
        System.out.println("\n🔔 Notificando " + observers.size() + " observer(s)...");
        
        for (PrecoObserver observer : observers) {
            observer.atualizar(acao, precoAnt, precoAtual, variacao);
        }
    }
    
    @Override
    public int contarObservers() {
        return observers.size();
    }
    
    // ========================================
    // Novo método que dispara notificações
    // ========================================
    
    public void atualizarPreco(Acao acao, String fonteDados) {
        double precoAnterior = precosAnteriores.getOrDefault(acao.getCodigo(), 0.0);
        
        // Busca novo preço
        Cotacao novaCotacao = buscarCotacaoAtual(acao.getCodigo(), fonteDados);
        double precoAtual = novaCotacao.getPreco();
        
        // Atualiza cache
        precosAnteriores.put(acao.getCodigo(), precoAtual);
        acao.setPrecoAtual(precoAtual);
        
        // 🔔 NOTIFICA OBSERVERS AUTOMATICAMENTE!
        if (precoAnterior > 0.0 && precoAnterior != precoAtual) {
            notificarObservers(acao, precoAnterior, precoAtual);
        }
    }
    
    // Método para simulação/demonstração
    public void simularMudancaPreco(Acao acao, double novoPreco) {
        double precoAnterior = acao.getPrecoAtual();
        acao.setPrecoAtual(novoPreco);
        precosAnteriores.put(acao.getCodigo(), novoPreco);
        
        notificarObservers(acao, precoAnterior, novoPreco);
    }
    
    // ... resto dos métodos existentes ...
}
```

### Passo 5: Usar na Aplicação

**Modificar:** `BolsaAplicacao.java`

```java
public void demonstrarObserverPattern() {
    CotacaoService cotacao = new CotacaoService();
    
    // Criar ação
    Acao petr4 = AcaoFactory.criarAcao(
        "ORDINARIA", "PETR4", "Petrobras PN", 30.0
    );
    
    // Registrar observers (inscrever)
    cotacao.registrarObserver(
        new EmailNotificacaoObserver("investidor@email.com", 0.02) // 2%
    );
    
    cotacao.registrarObserver(
        new SmsNotificacaoObserver("+5511999999999", 0.05) // 5%
    );
    
    cotacao.registrarObserver(
        new PushNotificacaoObserver("device-123", "João Silva", 0.03) // 3%
    );
    
    System.out.println("📋 Total de observers: " + cotacao.contarObservers());
    
    // Quando o preço muda → NOTIFICA AUTOMÁTICO!
    System.out.println("\n💹 Mudança de preço: +3.5%");
    cotacao.simularMudancaPreco(petr4, 31.05);
    
    // Mudança maior
    System.out.println("\n💹 Mudança de preço: -7%");
    cotacao.simularMudancaPreco(petr4, 28.88);
    
    // Remover observer
    System.out.println("\n🔄 Removendo observer de SMS...");
    // cotacao.removerObserver(smsObserver);
}
```

---

## 🎨 Diagrama do Padrão

```
┌─────────────────────┐
│   Subject           │  (Observable - quem muda)
│ CotacaoService      │
├─────────────────────┤
│ + attach(observer)  │ ← registrar
│ + detach(observer)  │ ← remover
│ + notify()          │ ← notificar todos
└──────────┬──────────┘
           │ notifica automaticamente
           ↓
┌─────────────────────┐
│   Observer          │  (interface)
│ + atualizar()       │
└──────────┬──────────┘
           │ implementa
           ↓
┌─────────────────────┐
│ EmailObserver       │  (observers concretos)
│ SmsObserver         │
│ PushObserver        │
│ LogObserver         │
└─────────────────────┘
```

---

## ✅ Benefícios

| Benefício | Descrição |
|-----------|-----------|
| **Reativo** | Notificação automática quando estado muda |
| **Desacoplamento** | Subject não conhece detalhes dos observers |
| **Extensível** | Fácil adicionar novos observers |
| **Dinâmico** | Add/remove observers em runtime |
| **Broadcast** | Notifica múltiplos objetos de uma vez |
| **Personalização** | Cada observer reage do seu jeito |

---

## 📝 Checklist de Implementação

- [ ] Criar pasta `observer/`
- [ ] Criar interface `PrecoObserver.java`
- [ ] Criar interface `PrecoSubject.java`
- [ ] Implementar `EmailNotificacaoObserver.java`
- [ ] Implementar `SmsNotificacaoObserver.java`
- [ ] Implementar `PushNotificacaoObserver.java`
- [ ] Implementar `LogNotificacaoObserver.java` (opcional)
- [ ] Modificar `CotacaoService.java` para implementar `PrecoSubject`
- [ ] Integrar com `BolsaAplicacao.java`
- [ ] Testar notificações
- [ ] Testar add/remove observers
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

1. **Evite Loops**: Observer não deve modificar Subject em `atualizar()`
2. **Performance**: Muitos observers podem causar lentidão
3. **Memória**: Remova observers não usados para evitar memory leak
4. **Ordem**: Não dependa da ordem de notificação
5. **Thread-Safe**: Use sincronização se múltiplas threads
6. **Filtros**: Cada observer pode ter seu próprio critério de notificação

---

## 🆚 Observer vs Outros Padrões

| Pattern | Diferença |
|---------|-----------|
| **Observer** | 1 → muitos, push, notificação automática |
| **Mediator** | muitos ↔ muitos, centralizado |
| **Command** | 1 → 1, pull, execução manual |
| **Pub-Sub** | Observer com broker/event bus |

---

## 🆚 Quando NÃO usar

- ❌ Relação simples 1-para-1
- ❌ Performance crítica com muitos observers
- ❌ Notificações síncronas causam problemas
- ❌ Dependências complexas entre observers

---

## 🔄 Variações do Pattern

### Push vs Pull

**Push (usado aqui):**
```java
observer.atualizar(acao, precoAnt, precoAtual, variacao);
// Subject envia todos os dados
```

**Pull:**
```java
observer.atualizar(subject);
// Observer busca dados do Subject quando necessário
```

### Observable do Java (Deprecated)

Java tinha `java.util.Observable` mas foi **descontinuado**. Use implementação própria como fizemos.

---

## 📚 Padrões Relacionados

- **Mediator**: Para gerenciar comunicação complexa
- **Singleton**: Subject pode ser singleton
- **Command**: Pode ser usado com observer para notificar comandos

---

## 🎯 Casos de Uso no Projeto

1. **Alertas de Preço**: Notificar quando ação atinge preço-alvo
2. **Atualização de UI**: Múltiplas views observam mesmo modelo
3. **Logs/Auditoria**: Observer de log registra todas as mudanças
4. **Notificações Multi-canal**: Email, SMS, Push simultaneamente
5. **Dashboards**: Atualização em tempo real

---

## 🔐 Segurança e Performance

### Evitar Memory Leak
```java
// Sempre remover observers quando não precisar mais
public void cleanup() {
    cotacaoService.removerObserver(this);
}
```

### Notificação Assíncrona (Avançado)
```java
@Override
public void notificarObservers(...) {
    ExecutorService executor = Executors.newCachedThreadPool();
    for (PrecoObserver obs : observers) {
        executor.submit(() -> obs.atualizar(...));
    }
}
```

---

**Padrão:** Comportamental  
**Complexidade:** ⭐⭐ (Fácil/Média)  
**Uso no Projeto:** Sistema de notificações reativo para mudanças de preço de ações

