package br.com.elo7.sonda.candidato.dto;

import java.util.List;

public class InputDTO {
	private int width; 
	private int height;
	private List<ProbeDTO2> probes;

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<ProbeDTO2> getProbes() {
		return probes;
	}
	public void setProbes(List<ProbeDTO2> probes) {
		this.probes = probes;
	}
}
