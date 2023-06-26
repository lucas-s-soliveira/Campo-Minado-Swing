package br.com.Lucas.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {
	private final int linha;
	private final int coluna;

	private boolean minado = false;
	private boolean aberto = false;
	private boolean marcado = false;

	private List<Campo> vizinhos = new ArrayList<>();
	private List<CampoObservador> observadores = new ArrayList<>();

	public Campo(int linha, int coluna) {
		this.coluna = coluna;
		this.linha = linha;
	}

	public void registrarObservador(CampoObservador observador) {

		observadores.add(observador);
	}

	private void notificarObservadores(CampoEvento evento) {
		observadores.stream().forEach(o -> o.eventoOcorreu(this, evento));
	}

	public boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int deltaGeral = deltaColuna + deltaLinha;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}

	void alternarMarcacao() {
		if (!aberto) {
			if (marcado) {
				notificarObservadores(CampoEvento.MARCAR);
			} else {
				notificarObservadores(CampoEvento.DESMARCAR);
			}
			// alternancia de marcacao
			marcado = !marcado;
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			if (minado) {
				notificarObservadores(CampoEvento.EXPLODIR);
				return true;
			}
			setAberto(true);

			notificarObservadores(CampoEvento.ABRIR);
			if (vizinhancaSegura()) {

				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		} else {
			return false;
		}
	}

	boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	void setAberto(boolean aberto) {
		this.aberto = aberto;
		if (aberto) {
			notificarObservadores(CampoEvento.ABRIR);
		}

	}

	void minar() {
		minado = true;

	}

	boolean isMinado() {
		return minado;
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}

	public boolean isMarcado() {
		return marcado;
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}

	long minasNaVizinhanca() {

		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reininciar() {
		aberto = false;
		minado = false;
		marcado = false;

	}

}
