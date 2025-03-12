# Sistema de Matrículas Universitário

Bem-vindo ao repositório do *Sistema de Matrículas*! Este projeto foi desenvolvido para informatizar o processo de matrículas de uma universidade, facilitando a gestão de disciplinas, alunos e professores.

## 🎯 Objetivo
Criar um sistema robusto de matrículas que permita aos alunos se inscreverem e cancelarem disciplinas, enquanto a secretaria e os professores têm acesso às informações essenciais para a organização acadêmica.

## 📘 Funcionalidades Implementadas

### 🏫 *Gestão Acadêmica*
- **Gerenciamento de Disciplinas:**
  - Cadastro de disciplinas com nome, créditos e capacidade de alunos.
  - Ativação de disciplinas apenas se houver pelo menos 3 alunos matriculados ao final do período de matrícula.
  - Limitação de matrículas em disciplinas (máximo de 60 alunos por turma).
  
- **Matrícula de Alunos:**
  - Inscrição em até 4 disciplinas obrigatórias e 2 optativas.
  - Cancelamento de matrículas dentro do período permitido.
  - Notificação ao sistema de cobranças após a conclusão da matrícula.

- **Consulta de Professores:**
  - Acesso à lista de alunos matriculados em suas disciplinas.
  
### 🔒 *Autenticação e Segurança*
- Login com senha para todos os usuários.
- Perfis diferenciados para alunos, professores e secretaria.

## 🛠️ Tecnologias Utilizadas
- **Linguagem:** Java
- **Modelagem:** Diagramas UML (Casos de Uso e Classes) no Figma
- **Interface:** Linha de comando
- **Persistência:** Arquivos ()

## 🚀 Processo de Desenvolvimento
1. **Sprint 01:** Modelagem de Casos de Uso e Histórias de Usuário (Markdown no README)
2. **Sprint 02:** Refinamento dos diagramas + Estrutura inicial do projeto Java com classes, atributos e stubs de métodos
3. **Sprint 03:** Implementação do protótipo funcional com interface em linha de comando e persistência em arquivos

## 📂 Organização do Repositório

```
├── /docs               # Modelos UML e documentação
├── /src                # Código-fonte Java (estrutura inicial)
├── /test               # Testes automatizados
└── README.md           # Documentação principal
```

## 📌 Regras Importantes
- As matrículas só podem ser realizadas durante o período definido pela universidade.
- Disciplinas com menos de 3 alunos serão canceladas automaticamente.
- Atualizações semanais no repositório são obrigatórias para avaliação do progresso.
- Interface gráfica não é obrigatória, mas pode ser um diferencial.

