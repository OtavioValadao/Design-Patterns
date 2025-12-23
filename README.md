# Design Patterns - Stock Trading System

Sistema de demonstração de padrões de projeto aplicados a um sistema de negociação de ações.

## 📁 Estrutura do Projeto

```
Design-Patterns/
├── src/
│   └── com/
│       └── patterns/
│           └── stocktrading/
│               ├── BolsaAplicacao.java       # Aplicação principal
│               ├── chain/                     # Chain of Responsibility
│               ├── facade/                    # Facade
│               ├── factorymethod/             # Factory Method
│               ├── model/                     # Modelos de domínio
│               ├── service/                   # Serviços
│               ├── singleton/                 # Singleton
│               ├── strategy/                  # Strategy
│               ├── template/                  # Template Method
│               └── util/                      # Utilitários
├── .gitignore
└── README.md
```

## 🎯 Padrões de Projeto Implementados

### 1. **Chain of Responsibility** (`chain/`)
Processa ordens de negociação através de uma cadeia de validações e handlers.

### 2. **Facade** (`facade/`)
Simplifica a interface do sistema de bolsa de valores.

### 3. **Factory Method** (`factorymethod/`)
Cria diferentes tipos de ações (Ordinárias, Preferenciais, ETFs).

### 4. **Singleton** (`singleton/`)
Gerencia configurações globais do sistema.

### 5. **Strategy** (`strategy/`)
Implementa diferentes estratégias de cálculo (Risco, Rentabilidade, Imposto).

### 6. **Template Method** (`template/`)
Define o esqueleto de processamento de ordens.

### 7. **Builder** (`model/OrdemBuilder.java`)
Constrói objetos complexos de Ordem de forma fluente.

## 🚀 Como Executar

```bash
# Compilar
javac -d out src/com/patterns/stocktrading/**/*.java

# Executar
java -cp out com.patterns.stocktrading.BolsaAplicacao
```

## 📦 Pacotes

- `com.patterns.stocktrading` - Pacote raiz
- `com.patterns.stocktrading.model` - Entidades do domínio
- `com.patterns.stocktrading.service` - Lógica de negócio
- `com.patterns.stocktrading.util` - Utilitários e helpers

## 📝 Notas

- Pasta `out/` é ignorada pelo Git (arquivos compilados)
- Projeto usa Java puro, sem frameworks externos
