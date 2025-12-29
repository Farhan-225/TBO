package org.example;

import java.util.Set;

public class Grammar {

    public static final Set<String> SUBJEK = Set.of(
            "aku", "kowe", "dheweke"
    );

    public static final Set<String> PREDIKAT = Set.of(
            "mangan", "ngombe", "masak", "sinau", "dolanan", "ngumbah"
    );

    public static final Set<String> OBJEK = Set.of(
            "sego", "wedang", "matematika", "bal", "montir", "sego goreng"
    );

    public static final Set<String> KET_TEMPAT = Set.of(
            "ing omah", "ing pawon", "ing taman", "ing lapangan", "ing garasi"
    );

    public static final Set<String> KET_WAKTU = Set.of(
            "saiki", "bengi iki", "sore iki"
    );
}
