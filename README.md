# 🍰 Sistema de Precificação para Confeitaria

API backend desenvolvida com Java e Spring Boot para cálculo inteligente de custos e precificação de produtos de confeitaria.

---

## 🚀 Sobre o projeto

Este projeto nasceu a partir da necessidade de transformar uma planilha de precificação em um sistema escalável e automatizado.

A aplicação permite calcular com precisão o custo real de um produto, considerando:

* Ingredientes
* Embalagens
* Mão de obra

---

## 🧠 Regra de Negócio

O sistema aplica as seguintes regras:

### 📦 Custo de Ingredientes

(precoPacote / quantidadePacote) * quantidadeUsada

### 📦 Custo de Embalagem

(precoPacote / quantidadePacote) * quantidadeUsada

### 👨‍🍳 Mão de Obra

horasTrabalhadas * custoHora

### 💰 Custo Total

ingredientes + embalagem + mão de obra

### 💸 Preço de Venda

custoTotal * markup

---

## 📌 Exemplo de resposta da API

```json
{
  "custoIngredientes": 25.0,
  "custoEmbalagem": 18.0,
  "custoMaoDeObra": 6.0,
  "custoTotal": 49.0,
  "custoPorPorcao": 8.16,
  "precoTotal": 73.5,
  "precoPorPorcao": 12.25
}
```

---

## 🛠️ Tecnologias utilizadas

* Java 17
* Spring Boot
* Maven
* Lombok

---

## 🧱 Arquitetura

O projeto segue uma arquitetura em camadas:

* **controller** → exposição dos endpoints REST
* **service** → regras de negócio
* **entity** → modelagem do domínio
* **dto** → padronização das respostas

---

## ⚙️ Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/SEU-USUARIO/SEU-REPO.git
```

2. Acesse o projeto:

```bash
cd nome-do-projeto
```

3. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

Ou rode direto pela IDE.

---

## 🔍 Principais endpoints

| Método | Endpoint                                 | Descrição                        |
| ------ | ---------------------------------------- | -------------------------------- |
| POST   | /products                                | Cadastrar produto                |
| POST   | /ingredients                             | Cadastrar ingrediente            |
| POST   | /packagings                              | Cadastrar embalagem              |
| POST   | /recipe-items                            | Adicionar ingrediente ao produto |
| POST   | /packaging-items                         | Adicionar embalagem ao produto   |
| GET    | /recipe-items/product/{id}/ficha-tecnica | Calcular ficha técnica           |

---

## 🚀 Próximas evoluções

* Integração com PostgreSQL
* Uso de JPA/Hibernate
* Interface frontend
* Autenticação de usuários
* Multiusuário

---

## 💡 Diferenciais do projeto

* Modelagem baseada em regra de negócio real
* Separação clara de responsabilidades
* API preparada para evolução (SaaS)
* Cálculo detalhado de custos

---

## 👨‍💻 Autor

Desenvolvido por Henrique 🚀
