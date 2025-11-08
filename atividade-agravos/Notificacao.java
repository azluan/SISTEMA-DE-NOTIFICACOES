public class Notificacao {
    private String nomePaciente;
    private int idade;
    private String sexo;
    private String bairro;
    private String agravo;
    private String dataNotificacao;
    private String escolaridade;
    private String racaCor;

    public Notificacao(String nomePaciente, int idade, String sexo, String bairro,
                       String agravo, String dataNotificacao, String escolaridade, String racaCor) {
        this.nomePaciente = nomePaciente;
        this.idade = idade;
        this.sexo = sexo;
        this.bairro = bairro;
        this.agravo = agravo;
        this.dataNotificacao = dataNotificacao;
        this.escolaridade = escolaridade;
        this.racaCor = racaCor;
    }

    public String getNomePaciente() { return nomePaciente; }
    public int getIdade() { return idade; }
    public String getSexo() { return sexo; }
    public String getBairro() { return bairro; }
    public String getAgravo() { return agravo; }
    public String getDataNotificacao() { return dataNotificacao; }
    public String getEscolaridade() { return escolaridade; }
    public String getRacaCor() { return racaCor; }

    @Override
    public String toString() {
        return "\nPaciente: " + nomePaciente +
               "\nIdade: " + idade +
               "\nSexo: " + sexo +
               "\nBairro: " + bairro +
               "\nAgravo: " + agravo +
               "\nData: " + dataNotificacao +
               "\nEscolaridade: " + escolaridade +
               "\nRaça/Cor: " + racaCor + "\n";
    }
}
