# IFSC Eventos - Projeto Integrador

Este é um projeto Android desenvolvido em Kotlin para o gerenciamento e inscrição em eventos e cursos do IFSC (Instituto Federal de Santa Catarina). O aplicativo permite que alunos e servidores se inscrevam em atividades, acompanhem notícias recentes e monitorem seu progresso em tarefas específicas.

## 🚀 Funcionalidades

- **Autenticação**: Login e Cadastro de usuários utilizando Firebase Auth.
- **Inscrição Multiperfil**: Telas dedicadas para inscrição de Alunos e Professores/Técnicos.
- **Notícias e Destaques**: Visualização de cursos ofertados e notícias recentes na tela principal.
- **Gestão de Tarefas**: Acompanhamento de progresso em disciplinas ou eventos através de uma interface de "Todo List".
- **Integração com Firebase**: Armazenamento e autenticação segura.

## 🛠 Tecnologias Utilizadas

- **Linguagem**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: XML Layouts com [Material Design 3](https://m3.material.io/)
- **Arquitetura**: ViewBinding para interação com componentes de UI.
- **Bibliotecas**:
  - [Firebase Auth](https://firebase.google.com/docs/auth): Para autenticação.
  - [Glide](https://github.com/bumptech/glide): Para carregamento de imagens.
  - [ConstraintLayout](https://developer.android.com/training/constraint-layout): Para layouts flexíveis.
  - [Leanback](https://developer.android.com/training/tv/playback/details): Suporte para interfaces de TV (componentes específicos).

## 📂 Estrutura do Projeto

- `app/src/main/java/.../projetointegradorabdiel`: Contém as Activities, Adapters e Lógica de Negócio.
  - `MainActivity.kt`: Ponto de entrada com notícias e menu principal.
  - `SignInActivity.kt` / `SignUpActivity.kt`: Fluxo de autenticação.
  - `InscricaoAluno.kt` / `InscricaoProfessorOuTecnico.kt`: Formulários de cadastro.
  - `TodoActivity.kt`: Gerenciamento de progresso.
- `app/src/main/res/layout`: Arquivos de interface XML.
- `app/src/main/res/drawable`: Recursos gráficos e ícones do IFSC.

## ⚙️ Como Executar

1. Clone o repositório.
2. Abra o projeto no **Android Studio (Koala ou superior)**.
3. Certifique-se de ter o arquivo `google-services.json` configurado (caso utilize Firebase).
4. Sincronize o Gradle.
5. Execute no Emulador ou dispositivo físico.

---
Desenvolvido por Abdiel como parte do Projeto Integrador.
