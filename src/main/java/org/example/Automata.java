package org.example;

public class Automata {

    public static AnalysisResult analyze(String kalimat) {

        AnalysisResult result = new AnalysisResult();

        String input = kalimat.toLowerCase().trim();

        // Cek Keterangan Waktu
        for (String kw : Grammar.KET_WAKTU) {
            if (input.contains(kw)) {
                result.adaKW = true;
            }
        }

        // Cek Keterangan Tempat
        for (String kt : Grammar.KET_TEMPAT) {
            if (input.contains(kt)) {
                result.adaKT = true;
            }
        }

        // Bersihkan keterangan
        String bersih = input;
        for (String kw : Grammar.KET_WAKTU) bersih = bersih.replace(kw, "");
        for (String kt : Grammar.KET_TEMPAT) bersih = bersih.replace(kt, "");

        // Tokenisasi kata
        String[] tokens = bersih.trim().split("\\s+");

        for (String token : tokens) {
            if (Grammar.SUBJEK.contains(token)) {
                result.adaS = true;
            } else if (Grammar.PREDIKAT.contains(token)) {
                result.adaP = true;
            } else if (Grammar.OBJEK.contains(token)) {
                result.adaO = true;
            }
        }

        // Validasi SPOK
        result.validSPOK =
                result.adaS &&
                        result.adaP &&
                        result.adaO &&
                        result.adaKT &&
                        result.adaKW;

        return result;
    }
}

