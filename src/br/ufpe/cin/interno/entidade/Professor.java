package br.ufpe.cin.interno.entidade;


public class Professor {


		private String nome = "";
		private String urlImagem = "";
		private String[] areasInteresse;
		private String fone = "";
		private String fax = "";
		private String pagina = "";
		private String email = "";
		private String lattes = "";
		private String sala = "";
		
		
		public Professor(String nome, String urlImagem, String areasInteresse,
				String fone, String fax, String pagina, String email, String lattes, String sala) {
			super();
			this.setNome(nome);
			this.urlImagem = urlImagem;
			this.areasInteresse = areasInteresse.split(",");
			this.fone = fone;
			this.fax = fax;
			this.pagina = pagina;
			this.email = email;
			this.lattes = lattes;
			this.setSala(sala);
		}
		
		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getUrlImagem() {
			return urlImagem;
		}

		public void setUrlImagem(String urlImagem) {
			this.urlImagem = urlImagem;
		}

		public String[] getAreasInteresse() {
			return areasInteresse;
		}

		public void setAreasInteresse(String[] areasInteresse) {
			this.areasInteresse = areasInteresse;
		}

		public String getFone() {
			return fone;
		}

		public void setFone(String fone) {
			this.fone = fone;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getPagina() {
			return pagina;
		}

		public void setPagina(String pagina) {
			this.pagina = pagina;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getLattes() {
			return lattes;
		}

		public void setLattes(String lattes) {
			this.lattes = lattes;
		}

		public String getSala() {
			return sala;
		}

		public void setSala(String sala) {
			this.sala = sala;
		}

		
	
}
