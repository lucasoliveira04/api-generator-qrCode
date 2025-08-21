# Gerador de Pix via QR Code

Esta API permite gerar QR Codes para pagamentos via Pix.
Ela recebe os dados do pagamento e retorna um QR Code que pode ser lido por qualquer aplicativo compatível com Pix.

---

## Tecnologias Utilizadas

* **Java 17**
* **Spring Boot**
* **Maven**
* **Google Zxing** (biblioteca para geração de QR Code)

---

## Como Utilizar

Para utilizar a API, faça uma requisição **GET** para o endpoint, passando os parâmetros necessários.

### Endpoint Local

```bash
http://localhost:8080/api/v1/qrcode/generatePix
```

### Endpoint de Produção

```bash
http://54.234.252.204:8080/api/v1/qrcode/generatePix
```

### Exemplo de Requisição

```bash
curl -X GET "http://54.234.252.204:8080/api/v1/qrcode/generatePixQRCode?pixKey=<CHAVE_PIX>&nome=Lucas%20Oliveira%20Campos&cidade=SaoPaulo&valor=10.00&txid=0890382"
```

---

## Parâmetros

| Parâmetro | Descrição                                                                 | Obrigatório                          | Exemplo                     |
| --------- | ------------------------------------------------------------------------- | ------------------------------------ | --------------------------- |
| `pixKey`  | Chave Pix do destinatário (email, telefone, CPF, CNPJ ou chave aleatória) | Sim                                  | `camposdlucasoli@gmail.com` |
| `nome`    | Nome do destinatário                                                      | Sim                                  | `Lucas Oliveira Campos`     |
| `cidade`  | Cidade do destinatário                                                    | Sim                                  | `SaoPaulo`                  |
| `valor`   | Valor do Pix                                                              | Não (default `0.00`)                 | `10.00`                     |
| `txid`    | ID da transação                                                           | Não (default: gerado aleatoriamente) | `0890382`                   |

