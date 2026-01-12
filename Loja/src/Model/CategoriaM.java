package Model;

public class CategoriaM {

	private int codCategoria;
	private String nomeCategoria; 
	
	public int getCodCategoria() {
		return codCategoria;
	}
	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	

    public String toString()
    {
        return nomeCategoria;
    }
}
