# 🚀 Como Executar a Aplicação

## 🎯 Método 1: Script Automático (MAIS FÁCIL) ⭐

```bash
./run.sh
```

Esse script faz tudo automaticamente:
- ✅ Cria a pasta `out/`
- ✅ Compila todos os arquivos `.java`
- ✅ Executa a aplicação

---

## 🎯 Método 2: Comandos Manuais

### Passo 1: Criar pasta de saída

```bash
mkdir -p out
```

### Passo 2: Compilar

```bash
find src -name "*.java" -print | xargs javac -d out
```

### Passo 3: Executar

```bash
java -cp out com.patterns.stocktrading.BolsaAplicacao
```

---

## 🎯 Método 3: Tudo em Um Comando

```bash
mkdir -p out && find src -name "*.java" -print | xargs javac -d out && java -cp out com.patterns.stocktrading.BolsaAplicacao
```

---

## 📋 Estrutura do Projeto

```
Design-Patterns/
├── src/
│   └── com/
│       └── patterns/
│           └── stocktrading/
│               ├── BolsaAplicacao.java
│               ├── chain/
│               ├── facade/
│               ├── factorymethod/
│               ├── model/
│               ├── service/
│               ├── singleton/
│               ├── strategy/
│               ├── template/
│               └── util/
├── out/                    ← Gerado automaticamente
│   └── com/
│       └── patterns/
│           └── stocktrading/
│               └── *.class
├── run.sh                  ← Script de execução
└── COMO_EXECUTAR.md        ← Este arquivo
```

---

## ❌ Problemas Comuns e Soluções

### 1. "javac: command not found"

**Problema:** Java JDK não está instalado

**Solução:**
```bash
# Ubuntu/Debian
sudo apt install default-jdk

# Fedora/RHEL
sudo dnf install java-devel

# Arch Linux
sudo pacman -S jdk-openjdk
```

### 2. "Error: Could not find or load main class"

**Problema:** Compilação não foi feita ou pasta `out/` está errada

**Solução:**
```bash
# Limpar e recompilar
rm -rf out/
./run.sh
```

### 3. "package does not exist"

**Problema:** Não compilou todos os arquivos necessários

**Solução:** Use o comando `find` correto:
```bash
find src -name "*.java" -print | xargs javac -d out
```

### 4. "Permission denied: ./run.sh"

**Problema:** Script não tem permissão de execução

**Solução:**
```bash
chmod +x run.sh
./run.sh
```

---

## 🧹 Limpar Compilação

Para remover todos os arquivos compilados:

```bash
rm -rf out/
```

Para limpar e recompilar:

```bash
rm -rf out/ && ./run.sh
```

---

## 🔧 Verificar Instalação do Java

### Verificar versão do Java (runtime)
```bash
java -version
```

**Saída esperada:**
```
openjdk version "11.0.x" ou superior
```

### Verificar versão do compilador Java
```bash
javac -version
```

**Saída esperada:**
```
javac 11.0.x ou superior
```

### Se não estiver instalado:
```bash
# Instalar no Ubuntu/Debian
sudo apt update
sudo apt install default-jdk

# Verificar novamente
java -version
javac -version
```

---

## 📝 Notas Importantes

- ✅ A pasta `out/` é ignorada pelo Git (veja `.gitignore`)
- ✅ Sempre compile antes de executar
- ✅ Use Java 8 ou superior
- ✅ O script `run.sh` faz tudo automaticamente
- ✅ Não precisa especificar cada arquivo `.java` manualmente

---

## 🎮 Executando Outras Demos

Se você implementar os padrões **Command** ou **Observer**, pode criar métodos específicos em `BolsaAplicacao.java`:

```java
public static void main(String[] args) {
    BolsaAplicacao app = new BolsaAplicacao();
    
    // Escolha qual demonstração rodar:
    app.executarDemonstracao();           // Demonstração padrão
    // app.demonstrarCommandPattern();    // Padrão Command
    // app.demonstrarObserverPattern();   // Padrão Observer
}
```

Depois recompile e execute:
```bash
./run.sh
```

---

## 🆘 Precisa de Ajuda?

1. Verifique se está na pasta raiz do projeto
2. Verifique se o Java está instalado (`java -version`)
3. Tente limpar e recompilar (`rm -rf out/ && ./run.sh`)
4. Verifique os arquivos de guia:
   - `COMMAND_PATTERN.md` - Guia do padrão Command
   - `OBSERVER_PATTERN.md` - Guia do padrão Observer
   - `README.md` - Documentação geral do projeto

---

**Última atualização:** Dezembro 2025  
**Versão Java requerida:** 8 ou superior  
**SO testado:** Linux (Ubuntu/Debian)
