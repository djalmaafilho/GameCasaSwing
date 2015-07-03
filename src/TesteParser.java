public class TesteParser {

	public static void main(String[] args) {
		String str = "<div id=\"slideShow\">"
				+ "<div id=\"slideShowItems\">"
				+ "<div class=\"sld\">"
				+ "<img alt=\"\" src=\"../site/arquivo_adm/imagens/10_20121015143653_banner_slideroffice.jpg\" border=\"0\" id=\"img93\" />"
				+ "</div>"
				+ "<div class=\"sld\">"
				+ "<img alt=\"\" src=\"../site/arquivo_adm/imagens/10_20120928102714_banner_slider.jpg\" border=\"0\" id=\"img92\" />"
				+ "</div>" + "</div>" + "</div>";
		parse(str);
		
	}
	
	public static String parse(String str){
		String[] tags = str.split("<div class=\"sld\">");
		
		for (String string : tags) {
			if(string.contains("<img")){
				string = string.substring(string.indexOf("<img"), string.indexOf("/>"));
				string = string.substring(string.indexOf("src="),string.indexOf("border"));
				System.out.println(string.replace("src=\"", "").replace("\"", ""));
				
			}
		}
		
		System.out.println(tags.length);;
		
		return str;
	}
}
