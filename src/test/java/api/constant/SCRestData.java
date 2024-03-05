package api.constant;

import utils.Utilities;

public class SCRestData {
    public static final String SC_BASE_URI = Utilities.getValueOfDataConf("apiData.common.baseUri");
    public static final String CHAM_CHUNG_TU_URL = Utilities.getValueOfDataConf("apiData.chamChungTu.url");
    public static final String DUMMY_BASE_URI = Utilities.getValueOfDataConf("apiData.common.dummyUri");
    public static final String POST_REQUEST_DUMMY = Utilities.getValueOfDataConf("apiData.dummyPostApi.url");
}
