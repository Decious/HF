package PhraseGenerator;

public class PhrGen {
    public static void main(String[] args) {
            String [] wordListOne ={"круглосуточный", "трех-звенный","30000-футовый","взаимный","обоюдный выигрыш","фронтэнд",
        "на основе веб-технологий", "проникающий", "умный", "шесть сигм", "метод критического пути", "динамичный"};
        String [] wordListTwo ={"уполномоченный", "трудный", "чистый продукт", "ориентированный", "центральный", "распределенный",
        "кластеризованный", "фирменный", "нестандартный ум", "позиционированный", "сетевой", "сфокусированный", "использованный с выгодой",
        "выровненный", "нацеленный на", "общий", "совместный", "ускоренный"};
        String [] wordListThree ={"процесс", "пункт  разгрузки", "выход из положения", "тип структуры", "талант", "подход",
                "уровень завоеванного внимания", "портал", "период времени", "обзор", "образец", "пункт следования"};

        int firstL= wordListOne.length;
        int secondL= wordListTwo.length;
        int thirdL= wordListThree.length;



            int rand1= (int) (Math.random()*firstL);
            int rand2= (int) (Math.random()*secondL);
            int rand3= (int) (Math.random()*thirdL);
            String phraze = wordListOne[rand1]+" "+ wordListTwo[rand2]+" "+ wordListThree[rand3];
            System.out.println(phraze);


    }
}
