package seabattle;

import java.util.ArrayList;

public class DotComBust {

    //объявлюяем и инициализируем переменные, которые нам понадабятся
    private GameHelper GameHelper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void SetUpGame() {

        //создаем три объекта DotCom, даём  им имена и помещаем в ArrayList.
        DotCom one = new DotCom();
        one.setName("VK.com");
        DotCom two = new DotCom();
        two.setName("OK.ru");
        DotCom three = new DotCom();
        three.setName("Go2.com");
        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);

        //выводим краткие инструукции для пользователя
        System.out.println("Your Goal is to sink three 'sites'.");
        System.out.println("VK.com, OK.ru, Go2.com");
        System.out.println("Try to sink them for the least number of tries");

        //Повторяем с каждым объектом DotCom в списке
        for (DotCom dotComToSet : dotComList
        ) {
            //запрашиваем у вспомогательного объекта адрес "сайта".
        ArrayList<String> newLocation = GameHelper.placeDotCom(3);
            //вызываем сеттер из оюъекта DotCom чтобы передать ему местоположение,
            // которое только что получили от вспомогательного объекта.
            dotComToSet.setLocationCells(newLocation);
        }
    }

    private void StartPlaying () {
        //До тех пор, пока список объектов не станет пустым
        while (!dotComList.isEmpty()) {
            //получаем пользовательский ввод.
            String userGuess = GameHelper.getUserInput("make a turn");
            //вызываем наш метод checkUserGuess
            checkUserGuess(userGuess);
        }

        //вызываем наш метод finishGame
        finishGame();
    }

    private void finishGame(){
        //выводим сообщение о том, как пользователь прошел игру
        System.out.println("Все ваши сайты пошли ко дну!ЙОХОХО! Теперь ваши акции ничего не стоят!");
        if (numOfGuesses< 18) {
            System.out.println("Это заняло у вас всего " + numOfGuesses + " попыток.");
            System.out.println("Вы успели выбраться до того, как ваши вложения утонули!");
        }
        else {
            System.out.println("Суммарно " + numOfGuesses+ " попыток :( .  ");
            System.out.println("рыбы водят хороводы вокруг ваших вложений!");
        }
    }

    public static void main(String[] args) {
        //Создаем игровой объект
        DotComBust game = new DotComBust();
        //Просим игровой объект подготовить игру
        game.SetUpGame();
        //Говорим игровому объекту начать главный игровой цикл (продолжаем
        // запрашивать пользовательский ввод запрашивать пользовательский ввод
        // и проверять полученные данные)
        game.StartPlaying();
    }
    private void checkUserGuess(String userGuess) {
        //инкрементируем количество попыток которые сделал пользователь
        numOfGuesses++;
        // подразумеваем промах, пока не выяснім обратного
        String result = "YOU_MISSED   D:";
        // повторяем это для всех dotcom в списке
        for (DotCom dotComToTest : dotComList) {
            //просим DotCom проверить ход пользователя, ищем попадание(или потопление)
            result = dotComToTest.checkYourself(userGuess);
            //выбираемся из цикла раньше времени, нет смысла проверять другие сайты
            if (result.equals("GOTaHIT!")) {
                break;
            }
            if (result.equals("GotONE!")) {
                //ему пришел конец, поэтому удаляем его из списка "сайтов" и выходим из цикла
                dotComList.remove(dotComToTest);
                break;
            }
        }
        //выводим сообщение о том как пользователь прошел игру
        System.out.println(result);
    }



}
