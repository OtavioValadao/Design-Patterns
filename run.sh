#!/bin/bash
# Script para compilar e executar a aplicação

echo "🔨 Compilando o projeto..."

# Criar diretório out se não existir
mkdir -p out

# Compilar todos os arquivos .java recursivamente
find src -name "*.java" -print | xargs javac -d out

if [ $? -eq 0 ]; then
    echo "✅ Compilação concluída com sucesso!"
    echo ""
    echo "🚀 Executando a aplicação..."
    echo "================================"
    java -cp out com.patterns.stocktrading.BolsaAplicacao
else
    echo "❌ Erro na compilação!"
    exit 1
fi
