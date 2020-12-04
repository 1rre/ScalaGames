package es.tmoor.simearth

object Data {
  implicit class Cell(l: Long) {
    // -9600 to 9600 in 75m steps
    def altitude: Byte = (l & 0xFF).toByte

    // 200°K to 456°K in 1°k steps
    def meanTemp: Byte = ((l >>> 8) & 0xFF).toByte
    
    def rainfall: Byte = ((l >>> 16)& 0xFF).toByte

    // x%16 = drift direction, x/16 = drift speed
    def landMvmt: Byte = ((l >>> 24)& 0xFF).toByte

    // x%16 = wind direction, x/16 = wind speed
    def windMvmt: Byte = ((l >>> 32)& 0xFF).toByte

    // 5 Bits, 16 sp. with carnivore & herbivore
    def creature: Byte = ((l >>> 40)& 0x1F).toByte

    // 5 Bits, 32 climate classifications.
    def köppenCl: Byte = ((l >>> 45)& 0x1F).toByte

    // Is there a town there?
    def civilise: Boolean = ((l >>> 50) & 1) == 1

    // Is there a random event happening there?
    def randType: Byte = ((l >>> 51)& 0x1F).toByte

    // Has the player placed something there?
    def improved: Byte = ((l >> 56) & 0xFF).toByte
  }
}