package com.kurui.kums.market.util;

import java.util.ArrayList;
import java.util.List;

import com.kurui.kums.base.Constant;
import com.kurui.kums.base.util.StringUtil;
import com.kurui.kums.market.PriceReference;

public class PriceIndexStore {
	public static List<PriceReference> priceReferenceList = new ArrayList<PriceReference>();

	public static String getPriceReferencesNameByIds(String ids) {
		String info = "";
		if (ids != null && "".equals(ids) == false) {
			if (priceReferenceList != null && priceReferenceList.size() > 0) {
				String[] idArray = StringUtil.split(ids, ",");
				if (idArray != null) {
					for (int i = 0; i < idArray.length; i++) {
						long id = Constant.toLong(idArray[i]);
						if (id > 0) {

							for (int j = 0; j < priceReferenceList.size(); j++) {
								PriceReference priceReference = priceReferenceList
										.get(j);
								long tempId = priceReference.getId();

								if (tempId > 0) {
									if (id == tempId) {
										String tempName = priceReference
												.getName();
										info += tempName + ",";
										break;
									}
								} else {
									System.out.println("tempId is null..");
								}
							}
						} else {
							System.out.println("ids is null..");
						}
					}
				} else {
					System.out.println("codeArray is null");
				}
			} else {
				System.out.println("priceReferenceList is null..");
			}

			if (info.indexOf(",", info.length() - 1) > 1) {
				info = info.substring(0, info.length() - 1);
			}
		} else {
			System.out.println(ids + "ids is null ");
		}
		return info;
	}

	public static String getPriceReferencesNameByCodes(String codes) {
		String info = "系数:";
		if (codes != null && "".equals(codes) == false) {
			if (priceReferenceList != null && priceReferenceList.size() > 0) {
				String[] codeArray = StringUtil.split(codes, ",");
				if (codeArray != null) {
					for (int i = 0; i < codeArray.length; i++) {
						String code = codeArray[i];
						if (code != null && "".equals(code) == false) {

							for (int j = 0; j < priceReferenceList.size(); j++) {
								PriceReference priceReference = priceReferenceList
										.get(j);
								String tempCode = priceReference.getCode();

								if (tempCode != null
										&& "".equals(tempCode) == false) {
									if (code.equals(tempCode)) {
										String tempName = priceReference
												.getName();
										info += tempName + ",";
										break;
									}
								} else {
									System.out.println("tempCode is null..");
								}
							}
						} else {
							System.out.println("code is null..");
						}
					}
				} else {
					System.out.println("codeArray is null");
				}
			}
			if (info.indexOf(",", info.length() - 1) > 1) {
				info = info.substring(0, info.length() - 1);
			}
		} else {
			System.out.println(codes + " is null ");
		}
		return info;
	}

	public static List<PriceReference> getPriceReferenceList(Long type) {
		List<PriceReference> list = new ArrayList<PriceReference>();
		if (priceReferenceList != null) {
			for (int i = 0; i < priceReferenceList.size(); i++) {
				PriceReference reference = priceReferenceList.get(i);
				if (type != null && "".equals(type) == false) {
					Long tempType = Constant.toLong(reference.getType());
					if (type == tempType) {
						list.add(reference);
					}
				}
			}
		}
		return list;
	}

	public static List<PriceReference> getPriceReferenceList() {
		return priceReferenceList;
	}

	public static void setPriceReferenceList(
			List<PriceReference> priceReferenceList) {
		PriceIndexStore.priceReferenceList = priceReferenceList;
	}
}
