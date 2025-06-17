/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a3conexao;

/**
 *
 * @author noobg
 */
//Parametrização dos dados que eu quero recolher.

public class Sapataria {
    public static void main(String[] args) {
    
}

    private String nome;
    private String telefone;
    private String tipocalçado;
    private String tipodeconserto;


    public Sapataria(String nome, String telefone, String tipocalçado, String tipodeconserto) {
        this.nome = nome;
        this.telefone = telefone;
        this.tipocalçado = tipocalçado;
        this.tipodeconserto = tipodeconserto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipocalçado() {
        return tipocalçado;
    }

    public void setTipocalçado(String tipocalçado) {
        this.tipocalçado = tipocalçado;
    }

    public String getTipodeconserto() {
        return tipodeconserto;
    }

    public void setTipodeconserto(String tipodeconserto) {
        this.tipodeconserto = tipodeconserto;
    }
}
