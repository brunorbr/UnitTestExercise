package com.pucrs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaMagicaTest
{
    private ContaMagica testAccount;

    @BeforeEach
    public void setUp(){
        testAccount = new ContaMagica();
    }

    @Test
    public void CategorySilver_40kDeposit() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(40000);
        Assertions.assertEquals("SILVER", testAccount.getCategory());
    }

    @Test
    public void CategorySilver_60kDeposit() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(60000);
        Assertions.assertEquals("GOLD", testAccount.getCategory());
        Assertions.assertEquals(60000.00, testAccount.getSaldo());
    }

    @Test
    public void CategorySilver_250kDeposit() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(250000);
        Assertions.assertEquals("GOLD", testAccount.getCategory());
        Assertions.assertEquals(250000.00, testAccount.getSaldo());
    }

    @Test
    public void CategorySilver_InsuficientFunds() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(25000);
        Assertions.assertThrows(INVALID_OPER_EXCEPTION.class, () -> {
            testAccount.retirada(30000);
        });
    }

    @Test
    public void CategorySilver_Take0() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(2500);
        testAccount.retirada(0);
        Assertions.assertEquals(2500.00, testAccount.getSaldo());
    }

    @Test
    public void CategorySilver_TakeNegative() throws INVALID_OPER_EXCEPTION{
        testAccount.deposito(2500);
        Assertions.assertThrows(INVALID_OPER_EXCEPTION.class, () -> {
            testAccount.retirada(-75);
        });
    }

    @Test
    public void CategoryGold_190kDeposit() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(100000);
        testAccount.deposito(90000);
        Assertions.assertEquals(190900.00, testAccount.getSaldo());
        Assertions.assertEquals("GOLD", testAccount.getCategory());
    }

    @Test
    public void CategoryGold_280kDeposit() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(100000);
        testAccount.deposito(180000);
        Assertions.assertEquals(281800.00, testAccount.getSaldo());
        Assertions.assertEquals("PLATINUM", testAccount.getCategory());
    }

    @Test
    public void CategoryGold_Take30k() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(60000);
        testAccount.retirada(30000);
        Assertions.assertEquals(30000.00, testAccount.getSaldo());
        Assertions.assertEquals("GOLD", testAccount.getCategory());
    }

    @Test
    public void CategoryGold_Take40k() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(60000);
        testAccount.retirada(40000);
        Assertions.assertEquals(20000.00, testAccount.getSaldo());
        Assertions.assertEquals("SILVER", testAccount.getCategory());
    }

    @Test
    public void CategoryGold_130kDeposit() throws INVALID_OPER_EXCEPTION {
        testAccount.deposito(50000);
        testAccount.deposito(130000);
        Assertions.assertEquals(181300.00, testAccount.getSaldo());
        Assertions.assertEquals("GOLD", testAccount.getCategory());
    }

    @Test
    public void CategoryPlatinum_take30k() throws INVALID_OPER_EXCEPTION{
        testAccount.deposito(50000);
        testAccount.deposito(150000);
        testAccount.retirada(30000);
        Assertions.assertEquals(171500.00, testAccount.getSaldo());
        Assertions.assertEquals("PLATINUM", testAccount.getCategory());
    }

    @Test
    public void CategoryPlatinum_take190k() throws INVALID_OPER_EXCEPTION{
        testAccount.deposito(80000);
        testAccount.deposito(200000);
        testAccount.retirada(192000);
        Assertions.assertEquals(90000.00, testAccount.getSaldo());
        Assertions.assertEquals("GOLD", testAccount.getCategory());
    }

    @Test
    public void CategoryPlatinum_take279999() throws INVALID_OPER_EXCEPTION{
        testAccount.deposito(80000);
        testAccount.deposito(200000);
        testAccount.retirada(281999);
        Assertions.assertEquals(1.00, testAccount.getSaldo());
        Assertions.assertEquals("GOLD", testAccount.getCategory());
    }

    @Test
    public void CategoryPlatinum_10000kDeposit()throws INVALID_OPER_EXCEPTION{
        testAccount.deposito(80000);
        testAccount.deposito(200000);
        testAccount.deposito(100000);
        Assertions.assertEquals(384500, testAccount.getSaldo());
    }
}