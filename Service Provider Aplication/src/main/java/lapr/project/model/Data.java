package lapr.project.model;

import java.io.Serializable;

/**
 *
 * @author Utilizador
 */
public class Data  implements Serializable{
        
    /**
     * O ano da data.
     */
    private int ano;
    
    /**
    * O mês da data.
    */
    private int mes;
     
    /**
    * O dia da data.
    */
    private int dia;
        
    /**
    * O ano da data por omissão.
    */
    private static final int ANO_POR_OMISSAO = 1;
    
    /**
    * O mês da data por omissão.
    */
    private static final int MES_POR_OMISSAO = 1;
    
    /**
    * O dia da data por omissão.
    */
    private static final int DIA_POR_OMISSAO = 1;
    
    /**
    * Nomes dos dias da semana.
    */
    private static String[] nomeDiaDaSemana = {"Domingo", "Segunda-feira",
                                               "Terça-feira", "Quarta-feira",
                                               "Quinta-feira", "Sexta-feira",
                                               "Sábado"};
    
    /**
    * Número de dias de cada mês do ano.
    */
    private static int[] diasPorMes = {  0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 
                                        31, 30, 31};
    
    /**
    * Nomes dos meses do ano.
    */
    private static String[] nomeMes = {"Inválido", "Janeiro", "Fevereiro",
                                       "Março", "Abril", "Maio", "Junho",
                                       "Julho", "Agosto", "Setembro",
                                       "Outubro", "Novembro", "Dezembro"};
   
    /**
     * Constrói uma instância de Data recebendo o ano, o mês e o dia. 
     * 
     * @param ano o ano da data
     * @param mes o mês da data
     * @param dia o dia da data
     */
    public Data(int ano, int mes, int dia) {        
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;              
    }
    
    /**
     *
     * @param data
     */
    public Data(String data) {
        try{
			StringBuilder sb = new StringBuilder();
            String[] info = data.split("/");
			if (info[2].length() == 4) {
				sb.append(info[2].charAt(2));
				sb.append(info[2].charAt(3));
				info[2] = sb.toString();
			}
            if(info.length == 3){
                ano = Integer.parseInt(info[2].trim());
                mes = Integer.parseInt(info[0].trim());
                dia = Integer.parseInt(info[1].trim());
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }            
    }

    /**
     * Constrói uma instância de Data com a data por omissão.  
     */
    public Data() {
        ano = ANO_POR_OMISSAO;
        mes = MES_POR_OMISSAO;
        dia = DIA_POR_OMISSAO;
    }
    
    /**
     * Devolve o ano da data.
     * 
     * @return ano da data
     */
    public int getAno() {
        return ano;
    }
    
    /**
     * Devolve o mês da data.
     * 
     * @return mês da data
     */
    public int getMes() {
        return mes;
    }

    /**
     * Devolve o dia da data.
     * 
     * @return dia da data
     */    
    public int getDia() {
        return dia;
    }
    
    
 
   
    /**
     * Devolve a data no formato:%04d/%02d/%02d.
     * 
     * @return caraterísticas da data
     */
    public String toAnoMesDiaString() {
        return String.format("%d/%02d/%02d", ano, mes, dia);
    }
	
   
   

}