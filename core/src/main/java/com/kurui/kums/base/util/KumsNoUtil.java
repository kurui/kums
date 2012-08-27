package com.kurui.kums.base.util;


public class KumsNoUtil extends NoDBUtil {
	public String getBudgetNo() {
		return getNewNo("B", "yyyyMMdd", 4); // B
	}

	public String getFinanceOrderNo() {
		return getNewNo("O", "yyyyMMdd", 4); // O
	}

	public String getFinanceGroupNo() {
		return getNewNo("G", "yyyyMMdd", 6);// F
	}

	public String getStatementNo() {
		return getNewNo("S", "yyyyMMdd", 6);// S
	}

	public String getAgentNo() {
		return getNewNo("A", "yyyyMMdd", 6);// A
	}

	public String getProductNo() {
		return getNewNo("P", "yyyyMMdd", 6);// P
	}

	public String getEstateDishNo() {
		return getNewNo("ED", "yyyyMMdd", 6);
	}

	public String getApartmentNo() {
		return getNewNo("AP", "yyyyMMdd", 6);
	}
}
