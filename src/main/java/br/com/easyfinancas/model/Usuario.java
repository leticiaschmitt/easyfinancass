package br.com.easyfinancas.model;

public class Usuario extends Pessoa {
    private boolean notificacoesAtivas = true;

    public Usuario() { super(); }

    public Usuario(String nome, String email) {
        super(nome, email);
    }

    public void exibirPerfil() {
        System.out.println("Usuário → " + getNome()
                + " | email: " + getEmail()
                + " | notificações " + (notificacoesAtivas ? "ativas" : "inativas"));
    }

    public void ativarNotificacoes() {
        notificacoesAtivas = true;
        System.out.println("Notificações ativadas para " + getNome());
    }

    public void desativarNotificacoes() {
        notificacoesAtivas = false;
        System.out.println("Notificações desativadas para " + getNome());
    }

    public boolean isNotificacoesAtivas() { return notificacoesAtivas; }
}
