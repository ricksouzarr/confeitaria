# 🍰 Confeitaria — API de Precificação

API REST desenvolvida em Java com Spring Boot para cálculo de custos e precificação de produtos de confeitaria.

O projeto nasceu a partir de uma planilha real de precificação usada por uma confeiteira, transformada em um sistema escalável e automatizado.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?style=flat-square)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow?style=flat-square)

---

## Sobre o projeto

Confeiteiros costumam usar planilhas para calcular o preço de venda dos seus produtos. Esse sistema substitui essa planilha por uma API estruturada, capaz de calcular com precisão o custo real de cada produto considerando ingredientes, embalagens e mão de obra.

A arquitetura foi pensada para evoluir: o projeto está preparado para integração com banco de dados, autenticação e futuramente se tornar um produto SaaS.

---

## Regras de negócio

### Custo de ingrediente por receita
```
custo = (precoPacote / quantidadePacote) × quantidadeUsada
```

### Custo de embalagem por receita
```
custo = (precoPacote / quantidadePacote) × quantidadeUsada
```

### Mão de obra
```
custo = horasTrabalhadas × custoHora
```

### Custo total
```
custoTotal = custoIngredientes + custoEmbalagens + custoMaoDeObra
```

### Preço de venda
```
precoTotal      = custoTotal × markup
precoPorPorcao  = (custoTotal / rendimento) × markup
```

---

## Exemplo de resposta — Ficha Técnica

`GET /recipe-items/product/1/ficha-tecnica`

```json
{
  "custoIngredientes": 31.46,
  "custoEmbalagem": 17.48,
  "custoMaoDeObra": 5.68,
  "custoTotal": 54.62,
  "custoPorPorcao": 9.10,
  "precoTotal": 81.93,
  "precoPorPorcao": 10.01
}
```

---

## Tecnologias

- **Java 17**
- **Spring Boot 3**
- **Maven**
- **Lombok**

---

## Arquitetura

O projeto segue arquitetura em camadas com separação clara de responsabilidades:

```
src/
└── main/java/com/gestao/confeitaria/
    ├── controller/   # Endpoints REST — sem regra de negócio
    ├── service/      # Toda a lógica de cálculo e orquestração
    ├── entity/       # Modelagem do domínio
    └── dto/          # Contratos de resposta da API
```

Cada entidade carrega seu próprio cálculo unitário (`getCustoUnitario()`, `getCustoTotal()`), mantendo a lógica próxima dos dados.

---

## Modelagem do domínio

```
Ingredient ──┐
             ├──▶ RecipeItem ──┐
Packaging ───┤                 ├──▶ Product ──▶ FichaTecnicaResult
             └──▶ PackagingItem┘
Labor ───────────────────────────────────────▶
Unit (enum de medidas)
```

---

## Pré-requisitos

- Java 17+
- Maven 3.8+

---

## Como executar

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/confeitaria.git

# Entre na pasta
cd confeitaria

# Suba a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/units` | Cadastrar unidade de medida |
| `GET` | `/units` | Listar unidades |
| `POST` | `/ingredients` | Cadastrar ingrediente |
| `GET` | `/ingredients` | Listar ingredientes |
| `POST` | `/packagings` | Cadastrar embalagem |
| `GET` | `/packagings` | Listar embalagens |
| `POST` | `/labor` | Configurar mão de obra |
| `GET` | `/labor` | Consultar mão de obra |
| `POST` | `/products` | Cadastrar produto |
| `GET` | `/products` | Listar produtos |
| `POST` | `/recipe-items` | Adicionar ingrediente ao produto |
| `GET` | `/recipe-items` | Listar itens de receita |
| `POST` | `/packaging-items` | Adicionar embalagem ao produto |
| `GET` | `/packaging-items` | Listar itens de embalagem |
| `GET` | `/recipe-items/product/{id}/ficha-tecnica` | **Calcular ficha técnica completa** |

---

## Roadmap

- [ ] Integração com PostgreSQL via JPA/Hibernate
- [ ] Containerização com Docker
- [ ] Dois markups por produto (total e por porção)
- [ ] Testes unitários dos cálculos
- [ ] Autenticação de usuários
- [ ] Suporte a múltiplos usuários (multitenancy)
- [ ] Interface frontend

---

## Autor

Desenvolvido por **Henrique** — [LinkedIn](https://www.linkedin.com/in/henrique-souzar/) · [GitHub](https://github.com/ricksouzarr)
