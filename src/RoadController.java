import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController
{
    private static double passengerCarMaxWeight = 3500.0; // kg Объявление переменной Double
    private static int passengerCarMaxHeight = 2000; // mm  Объявление переменной int
    private static int controllerMaxHeight = 3500; // mm  Изменил высоту с 4м до 3,5м Объявление переменной int

    private static int passengerCarPrice = 100; // RUB Объявление переменной int
    private static int cargoCarPrice = 250; // RUB Объявление переменной int
    private static int vehicleAdditionalPrice = 200; // RUB Объявление переменной int

    public static void main(String[] args)
    {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in);
        int carsCount = scanner.nextInt(); // Объявление переменной int

        for(int i = 0; i < carsCount; i++) // Объявление переменной int i
        {
            Car car = Camera.getNextCar();
            System.out.println(car);

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car); // Объявление переменной int
            if(price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car)
    {
        int carHeight = car.height;  // Объявление переменной int
        int price = 0; // Объявление переменной int
        if (carHeight > controllerMaxHeight)
        {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        }
        else if (carHeight > passengerCarMaxHeight)
        {
            double weight = car.weight; // Объявление переменной double
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight)
            {
                price = cargoCarPrice; // Изменил цену для грузовых автомобилей
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            //Легковой автомобиль
            else {
                price = passengerCarPrice; // Изменил цену для пассажирской машины
            }
        }
        else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}