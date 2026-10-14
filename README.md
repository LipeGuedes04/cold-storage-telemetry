# 🏢 Monitoramento de Câmara Fria - Cold Storage Security

## 🎯 O que o projeto faz?

O sistema simula o controle de telemetria e segurança térmica para uma câmara frigorífica industrial. O usuário registra uma série de **5 leituras de temperatura** consecutivas. O programa realiza a validação dos valores de entrada em tempo real (bloqueando dados fisicamente inconsistentes fora da faixa de -40°C a 10°C). Ao final da coleta, o sistema varre o histórico para renderizar um extrato completo, identificando automaticamente a **maior temperatura atingida** e calculando o **total de dias em que a câmara correu risco de degradação** (temperaturas acima de -18°C).

### 🔄 Fluxo da Aplicação
```text
[ Entrada de Temperatura ] ➔ [ Validação de Faixa (-40°C a 10°C) ] ➔ [ Histórico em Array ] ➔ [ Análise de Risco ] ➔ [ Relatório Térmico Final ]
```

---

## 🎨 Design do Código (Como as regras conversam)

Este projeto foi construído utilizando uma arquitetura baseada em **Vetores Fixos (Arrays Unidimensionais)** de precisão flutuante para persistência temporária de dados. O processamento das regras de negócio ocorre por meio de **Laços de Repetição Independentes (Loops For Sequenciais)** que separam a fase de captura da fase analítica. Veja a estrutura limpa do design:

```text
       ┌────────────────────────┐
       │   Entrada de Dados     │ (Primeiro loop 'for' inicia a leitura das posições)
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │ Filtro de Faixa Segura │ ◄───┐ (Loop 'while' de barreira contra valores irregulares)
       └───────────┬────────────┘     │
                   │                  │
                   ▼                  │ (Entrada inválida)
       ┌────────────────────────┐     │
       │ Salvamento no Array    │ ────┘
       └───────────┬────────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │   Módulo Analítico     │ (Segundo loop 'for' varre o vetor completo)
       └───────────┬────────────┘
                   │
         ┌─────────┴─────────┐
         ▼                   ▼
   [Análise de Máxima] [Análise de Risco]
   (Atualiza 'maior')  (Se > -18°C, diasRisco++)
         │                   │
         └─────────┬─────────┘
                   │
                   ▼
       ┌────────────────────────┐
       │  Relatório Concludente │ (Saída de dados com o parecer de segurança)
       └────────────────┘
```

### 📋 Mapeamento de Variáveis e Estruturas

| Estrutura / Variável | O que ela faz no código? |
| :--- | :--- |
| 🚀 `main.java` | Classe principal executável que orquestra a leitura do console, gerencia o vetor e processa os dados de telemetria. |
| 📊 `temperaturas` | Array fixo de tipo `double` com 5 posições (`new double[5]`), utilizado para indexar o histórico térmico. |
| 🌡️ `maior` | Variável de controle de extremos que rastreia a maior temperatura registrada no vetor ao longo da semana. |
| ⚠️ `diasRisco` | Contador de infrações ativado caso alguma temperatura ultrapasse a barreira de segurança estipulada de -18°C. |

---

## 🧠 Conceitos de Programação Praticados

* **Estrutura de Vetores Fixos (Arrays):** Implementação de arranjos unidimensionais com tamanho estático pré-definido na memória, ideais para amostragens de escopo fechado.
* **Separação de Responsabilidades por Loops:** Uso de laços de repetição `for` distintos para isolar a etapa de entrada de dados e validação da etapa de processamento estatístico.
* **Varredura de Extremos Dinâmicos:** Inicialização de variável de controle utilizando a primeira posição do vetor (`temperaturas[0]`) para garantir robustez na busca pelo maior elemento contido na lista.

---

## 💻 Exemplo Prático de Uso

Imagine a seguinte simulação de auditoria térmica contendo uma entrada irregular e dias de risco rodando direto no terminal:

```text
Insira a temperatura 1:
-20.5
Insira a temperatura 2:
15.0
Valor inválido, digite novamente
Insira a temperatura 2:
-15.0
Insira a temperatura 3:
-30.0
Insira a temperatura 4:
-12.2
Insira a temperatura 5:
-22.0
Temperatura 1 digitada: -20.5
Temperatura 2 digitada: -15.0
Temperatura 3 digitada: -30.0
Temperatura 4 digitada: -12.2
Temperatura 5 digitada: -22.0
========================================
======== RELATÓRIO FINAL ===============
A maior temperatura da semana foi: -12.2
Total dos dias que a câmara correu risco: 2
```
