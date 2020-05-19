/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author user
 */
public class MoneyChange {

    public MoneyChange() {
    }

    public double getAmoung(String valor) {
        double amount = 0, helpAmount = 0;
        /* It's just for emergency */
        String[] cost = valor.split(" ");
        for (String element : cost) {
            String[] nextValue = element.split(",");
            for (String number : nextValue) {
                if (this.isNumeric(number)) {
                    helpAmount = Double.parseDouble(number);
                    if (helpAmount > 0) {
                        amount = (helpAmount * 0.3);
                    }
                }
            }
        }
        return amount;
        /* It's just for emergency */
    }
    
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
