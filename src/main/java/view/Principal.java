package view;

import java.util.Scanner;

import controller.Conectar;

public class Principal {

	public static void main(String[] args) {
		Conectar controller_ssh = new Conectar();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("Quantos equipamentos você deseja se conectar?");
		int qtd = Integer.parseInt(input.nextLine());
		
		String comandos = "";
		String hosts[] = new String[qtd];
		int port = 22;

		System.out.println("Digite o Usuario");
		String usuario = input.nextLine();
		System.out.println("Digite a senha");
		String password = input.nextLine();

		for (int i = 0; i < qtd; i++) {
			System.out.println("Digite o IP do " + (i + 1) + " Servidor");
			hosts[i] = input.nextLine();
		}
		System.out.println("Digite os comandos a serem Executados");
		System.out.println("Digite ENVIAR para enviar os comados");

		while (true) {
			String comando = input.nextLine();
			if (comando.contains("ENVIAR")) {
				break;
			}
			comandos += comando + ";";
		}

		for (String host : hosts) {
			try {
				controller_ssh.ssh(usuario, password, host, port, comandos);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
