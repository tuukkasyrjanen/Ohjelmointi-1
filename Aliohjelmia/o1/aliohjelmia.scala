

// Tämä tiedosto on useimmista muista kurssin kooditiedostoista poiketen tarjolla sekä
// suomen- että englanninkielisenä (eikä vain englanniksi).



// Seuraavista alkumäärittelyistä ei tarvitse tässä vaiheessa välittää mitään.
// Ne liittyvät siihen, miten nämä funktiot on sijoitettu pakkaukseen; aiheesta luvussa 5.2.
package object o1 extends o1.util.ShortcutAliases {



  // KIRJOITA TEHTÄVISSÄ PYYDETYT FUNKTIOT SEURAAVIEN IMPORT-KÄSKYJEN ALLE:
  import scala.collection.mutable.Buffer
  import scala.math._

  def metreiksi(jalat: Double, tuumat: Double) : Double = {
    var metrit = ((tuumat + 12*jalat)*2.54)/100
    return metrit
  }

  def rivi(ruutu: Int) = ((ruutu) / 8)

  def sarake(ruutu: Int) = ((ruutu) % 8)

  def saesta(sanat: String, melodia: String) ={
    println(sanat)
    play(melodia)

  }

  def pystypalkki(leveys: Int) = {
    rectangle(leveys,10*leveys,Blue)

  }

  def pystypalkki(leveys: Int, vari: Color) = {
    rectangle(leveys,10*leveys,vari)

  }

  def liigapisteet(voitot: Int, tasuri: Int) = (voitot*3 + tasuri*1)

  def joukkueenTiedot(nimi: String, voitot: Int, tasuri: Int, havio: Int) = {
    val pisteet = liigapisteet(voitot, tasuri)
    val pelit = voitot + tasuri + havio
    val teksti = s"${nimi}: ${voitot}/${pelit} voittoa, ${tasuri}/${pelit} tasapeliä, ${havio}/${pelit} tappiota, ${pisteet} pistettä"
    teksti

  }



  // Alla on yhteen luvun 1.7 tehtävistä liittyvä virheellinen koodi, joka korjataan tehtävässä.
  def kahdella(melodia: String, eka: Int, toka: Int, tauonPituus: Int) = {
    val melodiaEkalla = "[" + eka + "]" + melodia
    val melodiaTokalla = "[" + toka + "]" + melodia
    val tauko = " " * tauonPituus
    val kahdestiSoitettuna = melodiaEkalla + tauko + melodiaTokalla
    kahdestiSoitettuna
  }

  def kurssiarvosana(arvosana: Int, tentti: Int, aktiivi: Int) = {
    var total = arvosana + tentti + aktiivi
    if (total > 5){
      total = 5
    }
    total
  }

  def sanallinenArvosana(tehtavaarvosana: Int, tenttibonus: Int, aktiivisuusbonus: Int) = {
    val kuvaukset = Buffer("hylätty", "välttävä", "tyydyttävä", "hyvä", "erittäin hyvä", "erinomainen")
    val arvosana = kurssiarvosana(tehtavaarvosana, tenttibonus, aktiivisuusbonus)
    kuvaukset(arvosana)
  }

  def tuplaaPisteet(puskuri: Buffer[Int], tupla: Int) = {
    puskuri(tupla-1) = puskuri(tupla-1) * 2
    puskuri
  }

  def sakko(pisteet: Buffer[Int], numero: Int, vahennys: Int) = {
    var vahennetty = 0
    if (pisteet(numero-1) > vahennys) {
      pisteet(numero-1) = pisteet(numero-1) - vahennys
      vahennetty = vahennys
    }
    else {
      vahennetty = pisteet(numero-1) - 1
      pisteet(numero-1) = 1
    }
    vahennetty
  }



  // TÄSSÄ ON ESIMERKKIFUNKTIOITA, JOIDEN TOTEUTUSTA KATSOTAAN LUVUISSA 1.7 JA 1.8.
  // NE ON SELITETTY TARKEMMIN LUKUJEN TEKSTISSÄ.

  def keskiarvo(eka: Double, toka: Double) = (eka + toka) / 2

  def huuda(lausahdus: String) = lausahdus + "!"

  def haukiOnKala(loppukaneetti: String) = {
    println("Kun hauki on vähärasvainen, sitä voidaan säilyttää pakastettuna jopa 6 kuukautta.")
    println("Vertailun vuoksi mainittakoon, että haukea rasvaisemman lahnan vastaava")
    println("säilymisaika on vain puolet eli 3 kuukautta.")
    println(loppukaneetti)
  }

  def piiri(sade: Double) = 2 * Pi * sade  // ei nyt käytössä luvuissa

  def etaisyys(x1: Double, y1: Double, x2: Double, y2: Double) = hypot(x2 - x1, y2 - y1)

  def punapallo(koko: Int) = circle(koko, Red)

  def isoinEtaisyys(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double) = {
    val eka = etaisyys(x1, y1, x2, y2)
    val toka = etaisyys(x1, y1, x3, y3)
    val kolmas = etaisyys(x2, y2, x3, y3)
    max(max(eka, toka), kolmas)
  }

  def verot(tulot: Double, tuloraja: Double, perusprosentti: Double, lisaprosentti: Double) = {
    val perusosa = min(tuloraja, tulot)
    val lisaosa = max(tulot - tuloraja, 0)
    perusosa * perusprosentti + lisaosa * lisaprosentti
  }

  def kokeilu1(luku: Int) = {
    println("Luku on: " + luku)
  }

  def kokeilu2(lukuja: Buffer[Int]) = {
    lukuja(0) = 100
  }

  def kokeilu3(luku: Int) = {
    println("Luku on: " + luku)
    luku + 1
  }

  def kokeilu4(sana: String) = {
    var luku = 1
    println(sana + ": " + luku)
    luku = luku + 1
    println(sana + ": " + luku)
    luku = luku + 1
    println(sana + ": " + luku)
    luku
  }

  def kokeilu5(aluksi: Int) = {
    var luku = aluksi
    luku = luku + 1
    luku = luku + 1
    luku = luku + 1
    luku
  }

  def testi1(teksti: String) = {
    println(teksti)
    "aina tämä"
  }

  def testi2(teksti: String) = {
    println(teksti)
    val vastaus = testi1(huuda(teksti))
    testi1(teksti)
    println("saatiin:")
    println(vastaus)
  }






  // ALLA ON FUNKTIOITA, JOITA KÄYTETÄÄN LUVUSSA 1.6. NIIDEN SISÄISTÄ TOIMINTAA EI TARVITSE ALUKSI YMMÄRTÄÄ.
  // Seuraavaa koodia ei ole kirjoitettu aloittelijaystävälliseen tyyliin.


  def poistaNegatiiviset(lukuja: Buffer[Int]): Unit = {
    lukuja --= lukuja.filter( _ < 0 )
  }


  def imdbLeffa(sija: Int) = movieData.sortBy( _._3 ).apply(sija - 1)._1

  def imdbAikavalinParas(alkuvuosi: Int, loppuvuosi: Int) =
    movieData
      .filter( leffa => leffa._2 >= alkuvuosi && leffa._2 <= loppuvuosi )
      .sortBy( _._3 )
      .apply(0)._1

  def imdbParhaatOhjaajat(leffojaVahintaan: Int) = {
    import o1.util.ConvenientCollection
    movieData
      .flatMap { case (_, _, _, _, ohjaajat) => ohjaajat.toList }
      .frequencies
      .filter( _._2 >= leffojaVahintaan )
      .toList.sortBy( -_._2 )
      .map { case (ohjaaja, leffoja) => s"$ohjaaja ($leffoja)" }
      .mkString(", ")
  }

  private lazy val movieData = {
    val Subdir   = "top_movies"
    val FileName = "omdb_movies_2015.txt"
    val rawLines = o1.util.readFileLines(s"$Subdir/$FileName").getOrElse( throw new Exception(s"Could not read the file $FileName, which is expected to be under $Subdir.") )
    val linesAsTokens = rawLines.map( _.split(";") )
    linesAsTokens.map( tokens => (tokens(0), tokens(1).toInt, tokens(2).toInt, tokens(3).toDouble, tokens(4).split(",")) )
  }


  def editointietaisyys(teksti1: String, teksti2: String) = o1.util.editDistance(teksti1, teksti2, 12)


  def animoi(kuvat: Buffer[Pic], kuviaSekunnissa: Double): Unit = {
    Animation.show(frames = kuvat, frameRate = kuviaSekunnissa)
  }

  def animoiFunktiolla(picGeneratingFunction: Int => Pic, numberOfPics: Int, picsPerSecond: Double): Unit = {
    Animation.generate(picGeneratingFunction, numberOfPics, picsPerSecond)
  }


  def kaanon(biisi: String, soittimet: Iterable[Int], viive: Int) = {
    import o1.sound.midi._
    import o1.util._

    val (melodia, tempo) = biisi match {
      case r"(.*?)$melodia(?:/([\d]+)$tempoOrNull)?" => (melodia, Option(tempoOrNull))
    }
    def alkutauko(monesko: Int) = " " * (monesko * viive atLeast 0 atMost melodia.length)
    val eriaikaiset = for ((soitin, monesko) <- soittimet.take(MaxVoices).zipWithIndex)
                        yield s"${alkutauko(monesko)}[$soitin]$melodia"
    eriaikaiset.mkString("&") + tempo.map( "/" + _ ).getOrElse("")
  }


  def sensuroi(teksti: String, rumatSanat: Iterable[String]) = {
    def piip(pituus: Int) = "[P" + "I" * max(0, pituus - 2) + "P]"
    def piippaaSana(teksti: String, sana: String) = teksti.replaceAll(sana, piip(sana.length))
    rumatSanat.foldLeft(teksti)(piippaaSana)
  }



  def pelaaPylpyrapelia(pelaaja: String) = {
    o1.gui.O1SwingDefaults()
    import o1.gui.Dialog._
    display("Tervetuloa PYLPYRÄÄTTÖRIIN, " + pelaaja + "!\nAlussa on kaksitoista pylpyrää.\n" +
            "Pelaajat ottavat vuorotellen 1 tai 2 pylpyrää.\nViimeisen pylpyrän saanut voittaa.", Centered)
    LazyList.iterate(12)(pelaaKierros).takeWhile(peliJatkuu).force
    display("Valitettavasti hävisit. Sori, " + pelaaja + ".\n", Centered)

    def pelaaKierros(jaljella: Int) = {
      pyydaValinta(jaljella).map( valittu => konePelaa(jaljella - valittu) ).getOrElse(0)
    }

    def onSallittuMaara(maara: Int) = maara == 1 || maara == 2

    def pyydaValinta(jaljella: Int) = {
      requestInt("Jäljellä on " + jaljella + " pylpyrää. Montako otat?", onSallittuMaara, "Ota 1 tai 2.", Centered)
    }

    def konePelaa(jaljella: Int) = {
      val koneOtti = parasValinta(jaljella)
      display("Otan " + koneOtti + " " + (if (koneOtti == 1) "pylpyrän" else "pylpyrää") + ".", Centered)
      jaljella - koneOtti
    }

    def peliJatkuu(jaljella: Int) = jaljella > 0

    def parasValinta(jaljella: Int) = jaljella % 3
  }


  def nayta(sana: String) = {
    println("Parametriksi saatiin: " + sana + ".")
    sana.length
  }

  def somalianLippu(leveys: Double) = {
    val tahti = star(leveys * (4.0/13.0), White)
    val tausta = rectangle(leveys, (2.0/3.0)*leveys, RoyalBlue)
    tahti.onto(tausta)
  }

  def suomenLippu(leveys: Double) = {
    val korkeus = (11.0/18.0) * leveys
    val pysty = rectangle((3.0/18.0) * leveys, korkeus, Blue)
    val vaaka = rectangle(leveys * (5.0/18.0), korkeus * (3.0/11.0), Blue)
    val tuplavaaka = rectangle(leveys * (10.0/18.0), korkeus * (3.0/11.0), Blue)
    val tausta = rectangle(leveys, korkeus, White)
    tuplavaaka.rightOf(vaaka.leftOf(pysty)).onto(tausta)
  }

  def nenita(kuva: Pic, sijainti: Pos) = {
    kuva.place(circle(15,Red), sijainti)
  }

}
