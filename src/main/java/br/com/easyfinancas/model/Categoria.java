package br.com.easyfinancas.model;

public class Categoria {
    private String nome;
    private String corHex;
    private TipoMovimentacao tipo;
    private boolean ativa = true;

    public Categoria() { }

    public Categoria(String nome, String corHex, TipoMovimentacao tipo) {
        this.nome = nome;
        this.corHex = corHex;
        this.tipo = tipo;
    }

    public void cadastrar() {
        System.out.println("Cadastrando categoria: " + nome + " (" + tipo + ")");
    }

    public void editar(String novoNome) {
        System.out.println("Editando categoria de '" + this.nome + "' para '" + novoNome + "'");
        this.nome = novoNome;
    }

    public void ativar() {
        this.ativa = true;
        System.out.println("Categoria '" + nome + "' ativada.");
    }

    public void desativar() {
        this.ativa = false;
        System.out.println("Categoria '" + nome + "' desativada.");
    }

    public void exibirResumo() {
        System.out.println("Resumo categoria → nome=" + nome + ", tipo=" + tipo + ", cor=" + corHex + ", ativa=" + ativa);
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCorHex() { return corHex; }
    public void setCorHex(String corHex) {
        if (corHex == null || !corHex.matches("^#[0-9A-Fa-f]{6}$")) {
            System.out.println("Cor inválida para categoria '" + nome + "'. Use formato #RRGGBB.");
        } else {
            this.corHex = corHex;
        }
    }
    public TipoMovimentacao getTipo() { return tipo; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }
    public boolean isAtiva() { return ativa; }

    @Override
    public String toString() {
        return "Categoria{nome='" + nome + "', tipo=" + tipo + ", cor='" + corHex + "', ativa=" + ativa + "}";
    }
}
