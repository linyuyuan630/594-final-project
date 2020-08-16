
public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		String line = "19103,1500,\"Ben,,,,,,, Franklin,Esq.\",285000";
		String[] lineInfo = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		System.out.println("line="+line);
		for(int i=0;i<lineInfo.length;i++) {
			System.out.println(lineInfo[i]);
		}
		
		
		Test test = new Test();
		System.out.println(test.displayValueOfFinePerCapita(0.02));
		System.out.println(test.displayValueOfFinePerCapita(1.02909889987));

	}
	
	private String displayValueOfFinePerCapita(Double origFine) {
		if(origFine==null) return null;
		
		String orig = String.valueOf(origFine);
		System.out.println("orig="+orig);
		String doubleInfo[] = orig.split("\\.");
		if(doubleInfo.length<1) return null;
		String intPart = doubleInfo[0];
		String fracPart="";
		if(doubleInfo.length>1) {
			fracPart = doubleInfo[1];
		}
		if(fracPart.length()<4) {
			fracPart = fracPart+"0000".substring(0,4-fracPart.length());
		}else {
			fracPart = fracPart.substring(0,4);
		}
		return intPart+"."+fracPart;
	}

}
