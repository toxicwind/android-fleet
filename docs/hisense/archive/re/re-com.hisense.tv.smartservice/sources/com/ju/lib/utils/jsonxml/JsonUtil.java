package com.ju.lib.utils.jsonxml;

import b.c.a.b;
import b.c.a.d;
import com.ju.lib.utils.log.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class JsonUtil {
    public static final String TAG = "JsonUtil";

    public static final class DataJsonFailCode {
        public static final String DATAJSON = "005";
        public static final String JSON_FILTER = "005020";
    }

    public static <E> E parse(InputStream inputStream, Class<E> cls) {
        if (inputStream != null && cls != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                E e2 = (E) b.b(cls).parse(inputStream);
                LogUtil.d(TAG, "parse(InputStream is, Class<E> jsonObjectClass), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return e2;
            } catch (IOException e3) {
                e3.printStackTrace();
                LogUtil.e(TAG, "JSONException parseFilterData", DataJsonFailCode.JSON_FILTER, e3);
            }
        }
        return null;
    }

    public static <E> List<E> parseList(String str, Class<E> cls) {
        if (str != null && cls != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                List<E> list = b.b(cls).parseList(str);
                LogUtil.d(TAG, "parseList(String jsonString, Class<E> jsonObjectClass), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return list;
            } catch (IOException e2) {
                e2.printStackTrace();
                LogUtil.e(TAG, "JSONException parseFilterData", DataJsonFailCode.JSON_FILTER, e2);
            }
        }
        return null;
    }

    public static <E> Map<String, E> parseMap(String str, Class<E> cls) {
        if (str != null && cls != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                Map<String, E> map = b.b(cls).parseMap(str);
                LogUtil.d(TAG, "parseMap(String jsonString, Class<E> jsonObjectClass), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return map;
            } catch (IOException e2) {
                e2.printStackTrace();
                LogUtil.e(TAG, "JSONException parseFilterData", DataJsonFailCode.JSON_FILTER, e2);
            }
        }
        return null;
    }

    public static <E> String serialize(E e2) {
        if (e2 == null) {
            return null;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            String strA = b.a(e2);
            LogUtil.d(TAG, "serialize(E object), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            return strA;
        } catch (IOException e3) {
            e3.printStackTrace();
            return null;
        } catch (NullPointerException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    public static <E> E parse(String str, Class<E> cls) {
        if (str == null || cls == null) {
            return null;
        }
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            E e2 = (E) b.b(cls).parse(str);
            LogUtil.d(TAG, "parse(String jsonString, Class<E> jsonObjectClass), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            return e2;
        } catch (IOException e3) {
            e3.printStackTrace();
            LogUtil.e(TAG, "JSONException parseFilterData", DataJsonFailCode.JSON_FILTER, e3);
            return null;
        }
    }

    public static <E> String serialize(List<E> list, Class<E> cls) {
        if (list != null && cls != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                String strSerialize = b.b(cls).serialize((List) list);
                LogUtil.d(TAG, "serialize(List<E> list, Class<E> jsonObjectClass), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return strSerialize;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static <E> String serialize(Map<String, E> map, Class<E> cls) {
        if (map != null && cls != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                String strSerialize = b.b(cls).serialize((Map) map);
                LogUtil.d(TAG, "serialize(Map<String, E> map, Class<E> jsonObjectClass), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return strSerialize;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static <E> E parse(String str, d<E> dVar) {
        if (str != null && dVar != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                E e2 = (E) b.a().parse(str);
                LogUtil.d(TAG, "parse(String jsonString, ParameterizedType<E> jsonObjectType), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return e2;
            } catch (IOException e3) {
                e3.printStackTrace();
                LogUtil.e(TAG, "JSONException parseFilterData", DataJsonFailCode.JSON_FILTER, e3);
            }
        }
        return null;
    }

    public static <E> String serialize(E e2, d<E> dVar) {
        if (e2 != null && dVar != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                String strSerialize = b.a().serialize(e2);
                LogUtil.d(TAG, "serialize(E object, ParameterizedType<E> parameterizedType), duration : ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                return strSerialize;
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }
}
