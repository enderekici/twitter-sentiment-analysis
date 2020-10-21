import lombok.Getter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cemserit on 3.09.2019.
 */
public enum Language {
    en("English (English)"),
    ja("Japanese (日本語"),
    ar("Arabic (العربية)"),
    es("Spanish (español)"),
    am("Amharic (አማርኛ)"),
    hy("Armenian (հայերեն)"),
    bn("Bangla (বাংলা)"),
    bg("Bulgarian (български)"),
    my("Burmese (မြန်မာ)"),
    ck("Central Kurdish (کوردیی ناوەندی)"),
    zh("Chinese (中文)"),
    da("Danish (dansk)"),
    dv("Divehi (Divehi)"),
    nl("Dutch (Nederlands)"),
    et("Estonian (eesti)"),
    fi("Finnish (suomi)"),
    fr("French (français)"),
    ka("Georgian (ქართული)"),
    de("German (Deutsch)"),
    el("Greek (Ελληνικά)"),
    gu("Gujarati (ગુજરાતી)"),
    ht("Haitian Creole (Haitian Creole)"),
    he("Hebrew (עברית)"),
    hi("Hindi (हिन्दी)"),
    hu("Hungarian (magyar)"),
    is("Icelandic (íslenska)"),
    id("Indonesian (Indonesia)"),
    it("Italian (italiano)"),
    kn("Kannada (ಕನ್ನಡ)"),
    km("Khmer (ខ្មែរ)"),
    ko("Korean (한국어)"),
    lo("Lao (ລາວ)"),
    lv("Latvian (latviešu)"),
    lt("Lithuanian (lietuvių)"),
    ml("Malayalam (മലയാളം)"),
    mr("Marathi (मराठी)"),
    ne("Nepali (नेपाली)"),
    no("Norwegian (norsk)"),
    or("Odia (ଓଡ଼ିଆ)"),
    ps("Pashto (پښتو)"),
    fa("Persian (فارسی)"),
    pl("Polish (polski)"),
    pt("Portuguese (português)"),
    pa("Punjabi (ਪੰਜਾਬੀ)"),
    ro("Romanian (română)"),
    ru("Russian (русский)"),
    sr("Serbian (српски)"),
    sd("Sindhi (سنڌي)"),
    si("Sinhala (සිංහල)"),
    sl("Slovenian (slovenščina)"),
    sv("Swedish (svenska)"),
    tl("Tagalog (Tagalog)"),
    ta("Tamil (தமிழ்)"),
    te("Telugu (తెలుగు)"),
    th("Thai (ไทย)"),
    bo("Tibetan (བོད་སྐད་)"),
    tr("Turkish (Türkçe)"),
    ur("Urdu (اردو)"),
    ug("Uyghur (ئۇيغۇرچە)"),
    vi("Vietnamese (Tiếng Việt)");

    @Getter
    private String language;

    Language(String language) {
        this.language = language;
    }

    public static Map<String, String> getLanguageMap() {
        Language[] languages = values();
        Map<String, String> languageMap = new TreeMap<>();
        for (Language language : languages) {
            languageMap.put(language.name(), language.getLanguage());
        }
        return languageMap;
    }
}
