package br.com.Lucas.cm.visao;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import br.com.Lucas.cm.modelo.Campo;
import br.com.Lucas.cm.modelo.CampoEvento;
import br.com.Lucas.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador {

	private final Color BG_PADRAO = new Color(184, 184, 184);
	private final Color BG_MARCADO = new Color(8, 179, 247);
	private final Color BG_EXPLODIR = new Color(189, 66, 68);
	private final Color BG_TEXTO_VERDE = new Color(0, 100, 0);

	private Campo campo;

	public BotaoCampo(Campo campo) {
		this.campo = campo;
		setBorder(BorderFactory.createBevelBorder(0));
		setBackground(BG_PADRAO);
		campo.registrarObservador(this);
	}

	@Override
	public void eventoOcorreu(Campo campo, CampoEvento evento) {
		switch (evento) {
		case ABRIR:
			aplicarEstiloAbrir();

			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;

		case EXPLODIR:
			aplicarEstiloExplidir();
			break;

		default:
			aplicarEstiloPadrao();
			break;
		}

	}

	private void aplicarEstiloPadrao() {
		// TODO Auto-generated method stub

	}

	private void aplicarEstiloExplidir() {
		// TODO Auto-generated method stub

	}

	private void aplicarEstiloMarcar() {
		// TODO Auto-generated method stub

	}

	private void aplicarEstiloAbrir() {
		// TODO Auto-generated method stub

	}

}
