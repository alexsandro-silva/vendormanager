package br.com.arsilva.vendormanager.javaapi.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public static boolean isValidCnpj(String cnpj) {
        Pattern pattern = Pattern.compile("0{14}|1{14}|2{14}|3{14}|4{14}|5{14}|6{14}|7{14}|8{14}|9{14}");
        Matcher matcher = pattern.matcher(cnpj);
        if (matcher.find() || cnpj.length() != 14) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num ,peso;

        try {
            //calculo 1º digito verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                num = (int)(cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char)((11 - r) + 48);
            }

            //calculo do 2º digito verificador;
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int)(cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char)((11 - r) + 48);
            }

            if (dig13 == cnpj.charAt((12)) && (dig14 == cnpj.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException error) {
            return (false);
        }

    }

    public static boolean isValidCpf(String cpf) {
        Pattern pattern = Pattern.compile("0{11}|1{11}|2{11}|3{11}|4{11}|5{11}|6{11}|7{11}|8{11}|9{11}");
        Matcher matcher = pattern.matcher(cpf);
        if (matcher.find() || cpf.length() != 11) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static boolean isMenorIdade(Date dataNascimento) {
        Date today = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(today);
        int currentYear = calendar.get(Calendar.YEAR);

        calendar.setTime(dataNascimento);
        int anoNascimento = calendar.get(Calendar.YEAR);

        return ((currentYear - anoNascimento) < 18);
    }
}
