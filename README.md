# 💳 PicPay Simplificado

O **PicPay Simplificado** é uma plataforma de pagamentos onde usuários podem **depositar e transferir dinheiro entre si**.

O sistema possui **dois tipos de usuários**:

- Usuários comuns
- Lojistas

Ambos possuem uma **carteira com saldo** e podem receber transferências.

---

# 👥 Tipos de Usuário

Existem dois tipos de usuários no sistema:

- **Usuário Comum**
- **Lojista**

---

# 📋 Dados Obrigatórios

Para ambos os tipos de usuário, o sistema deve armazenar as seguintes informações:

| Campo | Descrição |
|------|-----------|
| Nome Completo | Nome completo do usuário |
| Email | Deve ser **único no sistema** |
| Senha | Senha de acesso do usuário |
| Saldo | Valor disponível na carteira |

---

# 🔐 Permissões e Restrições

| Tipo de Usuário | Pode Receber? | Pode Transferir? |
|----------------|--------------|------------------|
| Usuário Comum | ✅ Sim | ✅ Sim |
| Lojista | ✅ Sim | ❌ Não |

### Observações

- Usuários comuns podem enviar dinheiro para:
  - outros usuários comuns
  - lojistas

- Lojistas **não podem enviar dinheiro**, apenas **receber transferências**.

---

# 💸 Regras de Negócio da Transferência

## Arquitetura

O serviço deve seguir o padrão:

**RESTful API**

---

## Validação de Saldo

Antes de realizar a transferência, o sistema deve validar se:
O usuário possui saldo suficiente


Caso contrário, a transferência **não deve ser realizada**.

---

## Autorizador Externo

Antes de finalizar a transferência, o sistema deve consultar um **serviço autorizador externo**.

A transferência **só deve ser concluída caso o serviço autorize**.

---

## Transacionalidade

A operação de transferência deve ser **transacional**.

Isso significa que:

- Se qualquer erro ocorrer durante o processo
- Toda a operação deve ser **revertida (rollback)**

---

## Notificações

Quando um usuário receber um pagamento, ele deve ser **notificado**.

As notificações podem ser:

- Email
- SMS

Esse envio é realizado por **um serviço externo de terceiros**.

### Tratamento de Falhas

O sistema deve estar preparado para lidar com:

- indisponibilidade
- instabilidade do serviço de notificação

---

# 🌐 Requisito Técnico (API)

## Endpoint de Transferência

**Método**
POST

**Caminho**
/transfer

**Content-Type**
application/json

---

# 📤 Exemplo de Requisição

```json
{
  "value": 100.0,
  "payer": 4,
  "payee": 15
}
