package com.pucrs;

public class ContaMagica {
    public static final int SILVER = 0;
    public static final int GOLD = 1;
    public static final int PLATINUM = 2;

    private double saldo;
    private int categoria;

    public ContaMagica(){
        this.saldo = 0;
        this.categoria = SILVER;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public void deposito(int valor) throws INVALID_OPER_EXCEPTION{
        if(valor < 0)
            throw new INVALID_OPER_EXCEPTION("Operação Inválida");
        else if(categoria == GOLD) {
            saldo += valor * 1.01;
            mudaCategoria(saldo);
        }
        else if(categoria == PLATINUM) {
            saldo += valor * 1.025;
            mudaCategoria(saldo);
        }
        else
            saldo += valor;
            mudaCategoria(saldo);

    }

    public void mudaCategoria(double saldo){
        if(categoria == SILVER){
            if(saldo >= 50000)
                categoria = GOLD;
        }
        else if (categoria == GOLD){
            if(saldo < 25000)
                categoria = SILVER;
            else if(saldo >= 200000)
                categoria = PLATINUM;
        }
        else if(categoria == PLATINUM){
            if(saldo <= 100000)
                categoria = GOLD;
        }
    }

    public void retirada(int valor) throws INVALID_OPER_EXCEPTION{
        if(valor > saldo || valor < 0)
            throw new INVALID_OPER_EXCEPTION("Operação Inválida");
        else
            saldo -= valor;
            mudaCategoria(saldo);
    }

    public String getCategory(){
        switch (categoria){
            case SILVER:
                return "SILVER";
            case GOLD:
                return "GOLD";
            case PLATINUM:
                return "PLATINUM";
            default:
                return "CATEGORIA INVALIDA";
        }
    }
}
