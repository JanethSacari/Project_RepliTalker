# RepliTalker

Aplicativo de conversas e chat desenvolvido exclusivamente para a comunicação entre integrantes da **Repliforce**.

## 🚀 Sobre o Projeto
O RepliTalker é uma plataforma de comunicação em tempo real que visa facilitar a interação e troca de informações dentro da organização Repliforce, garantindo um ambiente seguro e eficiente.

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Kotlin
- **Interface:** Jetpack Compose e XML Layouts (AndroidX)
- **Backend:** Firebase Core (Auth, Firestore, Storage)

## ⚙️ Configuração Necessária
Este projeto foi migrado para **AndroidX**.
Este projeto utiliza os serviços do Firebase para autenticação e banco de dados. Para executá-lo corretamente:

1. Crie um projeto no [Firebase Console](https://console.firebase.com/).
2. Adicione um aplicativo Android ao projeto com o ID do pacote `com.repliforce.replitalker`.
3. Baixe o arquivo `google-services.json` fornecido pelo Firebase.
4. Mova o arquivo para o diretório `app/` do projeto.
5. Certifique-se de configurar o **Firebase Authentication** e o **Cloud Firestore** no console.
