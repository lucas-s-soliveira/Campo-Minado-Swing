package br.com.Lucas.cm.visao;

import javax.swing.JFrame;

import br.com.Lucas.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {
	
	
	public TelaPrincipal() {
		Tabuleiro tabuleiro = new Tabuleiro(16, 30, 50);
	//	PainelTabuleiro painelTabuleiro = new PainelTabuleiro(tabuleiro);
		add(new PainelTabuleiro(tabuleiro));
		setTitle("Campo Minado");
		setSize(690,480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new TelaPrincipal();
		
	}
 

}
