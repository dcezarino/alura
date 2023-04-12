package novidades_java;

public class ConversaoTemperaturas {

	public static void main(String[] args) {
		
		double tempCelsius = 10.4;		
		double tempFahrenheit = (tempCelsius * 1.8) + 32;
		
        String mensagem = String.format("A temperatura de %f Celsius é equivalente a %f Fahrenheit", tempCelsius, tempFahrenheit);
        System.out.println(mensagem);
		
		int tempFahrenheitInt = (int) tempFahrenheit;
		
        System.out.println("A temperatura em Fahrenheit inteira é: " + tempFahrenheitInt);
		
	}
	
}
