import java.util.Scanner;

public class Main {

    enum RomanNumerals{
        I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10),L(50),C(100);
        int znach;

        public int getZnach() {
            return znach;
        }

        public void setZnach(int znach) {
            this.znach = znach;
        }
        RomanNumerals(int znach){
            this.znach=znach;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        String tmp=scanner.nextLine();
        System.out.println(calc(tmp));
    }
    public static String calc(String input) throws Exception {
        String[] tmp=input.split(" ");
        int length=tmp.length;
        if(length==1 || length==0 || length==2)
            throw new Exception("строка не является математической операцией ");
        if(length>3){
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        boolean countingSystem1=false;
        boolean countingSystem2=false;
        try{
            try{
                Integer.parseInt(tmp[0]);
                Integer.parseInt(tmp[2]);
                countingSystem1=true;
            }catch (Exception ignored){}
            RomanNumerals.valueOf(tmp[0]);
            RomanNumerals.valueOf(tmp[2]);
            countingSystem2=true;
        }
        catch(Exception e){
            if(!countingSystem1 && !countingSystem2){
                throw new Exception("используются одновременно разные системы счисления");
            }
        }
        int a,b;
        if(countingSystem1){
            a=Integer.parseInt(tmp[0]);
            b=Integer.parseInt(tmp[2]);
        }else{
            a=RomanNumerals.valueOf(tmp[0]).getZnach();
            b=RomanNumerals.valueOf(tmp[2]).getZnach();
        }

        int res = switch (tmp[1]) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default ->
                    throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *) ");
        };
        if(countingSystem1){
            return String.valueOf(res);
        }else{
            if(res<1){
                throw new Exception("в римской системе нет отрицательных чисел");
            }
            StringBuilder stringBuilder=new StringBuilder();
            while (res>=100){
                stringBuilder.append(RomanNumerals.C);
                res-=100;
            }
            while (res>=50){
                stringBuilder.append(RomanNumerals.L);
                res-=50;
            }
            while (res>=10){
                stringBuilder.append(RomanNumerals.X);
                res-=10;
            }
            for(RomanNumerals romanNumerals: RomanNumerals.values()){
                if(romanNumerals.getZnach()==res){
                    stringBuilder.append(romanNumerals.name());
                }
            }
            return stringBuilder.toString();
        }
    }
}