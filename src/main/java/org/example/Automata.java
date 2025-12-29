package org.example;

import java.util.ArrayList;
import java.util.List;

public class Automata {

    public static String analyze(String kalimat) {

        String input = kalimat.toLowerCase().trim();
        StringBuilder hasil = new StringBuilder();

        boolean adaS = false;
        boolean adaP = false;
        boolean adaO = false;
        boolean adaKT = false;
        boolean adaKW = false;

        List<String> kataTerdeteksi = new ArrayList<>();

        // Cek Keterangan Waktu
        for (String kw : Grammar.KET_WAKTU) {
            if (input.contains(kw)) {
                adaKW = true;
                kataTerdeteksi.add("Keterangan Waktu : " + kw);
            }
        }

        // Cek Keterangan Tempat
        for (String kt : Grammar.KET_TEMPAT) {
            if (input.contains(kt)) {
                adaKT = true;
                kataTerdeteksi.add("Keterangan Tempat : " + kt);
            }
        }

        // Tokenisasi sisa kata
        String bersih = input;
        for (String kw : Grammar.KET_WAKTU) bersih = bersih.replace(kw, "");
        for (String kt : Grammar.KET_TEMPAT) bersih = bersih.replace(kt, "");

        String[] tokens = bersih.trim().split("\\s+");

        for (String token : tokens) {

            if (Grammar.SUBJEK.contains(token)) {
                adaS = true;
                kataTerdeteksi.add("Subjek : " + token);
            }
            else if (Grammar.PREDIKAT.contains(token)) {
                adaP = true;
                kataTerdeteksi.add("Predikat : " + token);
            }
            else if (Grammar.OBJEK.contains(token)) {
                adaO = true;
                kataTerdeteksi.add("Objek : " + token);
            }
        }

        // Tampilkan hasil identifikasi
        hasil.append("HASIL ANALISIS:\n");
        for (String k : kataTerdeteksi) {
            hasil.append("- ").append(k).append("\n");
        }

        hasil.append("\nKESIMPULAN:\n");

        if (adaS && adaP && adaO && adaKT && adaKW) {
            hasil.append("✅ Kalimat VALID SPOK");
        } else {
            hasil.append("❌ Kalimat TIDAK VALID SPOK\n");
            hasil.append("Kelengkapan:\n");
            hasil.append("S: ").append(adaS ? "✓" : "✗").append("\n");
            hasil.append("P: ").append(adaP ? "✓" : "✗").append("\n");
            hasil.append("O: ").append(adaO ? "✓" : "✗").append("\n");
            hasil.append("KT: ").append(adaKT ? "✓" : "✗").append("\n");
            hasil.append("KW: ").append(adaKW ? "✓" : "✗");
        }

        return hasil.toString();
    }
}

