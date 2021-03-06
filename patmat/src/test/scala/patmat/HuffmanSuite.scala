package patmat

import org.junit._
import org.junit.Assert.assertEquals

class HuffmanSuite {
  import Huffman._

  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(
      Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5),
      Leaf('d', 4),
      List('a', 'b', 'd'),
      9
    )
    val frenchCode: CodeTree = Fork(
      Fork(
        Fork(
          Leaf('s', 121895),
          Fork(
            Leaf('d', 56269),
            Fork(
              Fork(
                Fork(Leaf('x', 5928), Leaf('j', 8351), List('x', 'j'), 14279),
                Leaf('f', 16351),
                List('x', 'j', 'f'),
                30630
              ),
              Fork(
                Fork(
                  Fork(
                    Fork(
                      Leaf('z', 2093),
                      Fork(
                        Leaf('k', 745),
                        Leaf('w', 1747),
                        List('k', 'w'),
                        2492
                      ),
                      List('z', 'k', 'w'),
                      4585
                    ),
                    Leaf('y', 4725),
                    List('z', 'k', 'w', 'y'),
                    9310
                  ),
                  Leaf('h', 11298),
                  List('z', 'k', 'w', 'y', 'h'),
                  20608
                ),
                Leaf('q', 20889),
                List('z', 'k', 'w', 'y', 'h', 'q'),
                41497
              ),
              List('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'),
              72127
            ),
            List('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'),
            128396
          ),
          List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'),
          250291
        ),
        Fork(
          Fork(Leaf('o', 82762), Leaf('l', 83668), List('o', 'l'), 166430),
          Fork(
            Fork(Leaf('m', 45521), Leaf('p', 46335), List('m', 'p'), 91856),
            Leaf('u', 96785),
            List('m', 'p', 'u'),
            188641
          ),
          List('o', 'l', 'm', 'p', 'u'),
          355071
        ),
        List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l',
          'm', 'p', 'u'),
        605362
      ),
      Fork(
        Fork(
          Fork(
            Leaf('r', 100500),
            Fork(
              Leaf('c', 50003),
              Fork(
                Leaf('v', 24975),
                Fork(Leaf('g', 13288), Leaf('b', 13822), List('g', 'b'), 27110),
                List('v', 'g', 'b'),
                52085
              ),
              List('c', 'v', 'g', 'b'),
              102088
            ),
            List('r', 'c', 'v', 'g', 'b'),
            202588
          ),
          Fork(Leaf('n', 108812), Leaf('t', 111103), List('n', 't'), 219915),
          List('r', 'c', 'v', 'g', 'b', 'n', 't'),
          422503
        ),
        Fork(
          Leaf('e', 225947),
          Fork(Leaf('i', 115465), Leaf('a', 117110), List('i', 'a'), 232575),
          List('e', 'i', 'a'),
          458522
        ),
        List('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'),
        881025
      ),
      List('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm',
        'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'),
      1486387
    )

  }
  @Test def `weight of a larger tree (10pts)` : Unit =
    new TestTrees {
      assertEquals(5, weight(t1))
    }
  @Test def `chars of a larger tree (10pts)` : Unit =
    new TestTrees {
      assertEquals(List('a', 'b', 'd'), chars(t2))
    }

  @Test def `string2chars hello world`: Unit =
    assertEquals(
      List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'),
      string2Chars("hello, world")
    )
  @Test def `make ordered leaf list for some frequency table (15pts)` : Unit =
    assertEquals(
      List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)),
      makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3)))
    )

  @Test def `combine of nil`: Unit = {
    assertEquals(
      Nil,
      combine(Nil)
    )
  }

  @Test def `combine of some leaf list (15pts)` : Unit = {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assertEquals(
      List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)),
      combine(leaflist)
    )
  }

  @Test def `decode french`: Unit = new TestTrees {
    val secret: List[Bit] = List(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0,
      1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0,
      0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)
    val decoded = decode(frenchCode, secret)
    assertEquals("huffmanestcool".toList, decoded)
  }

  @Test def `decode and encode a very short text should be identity (10pts)`
      : Unit = new TestTrees {
    assertEquals("ab".toList, decode(t1, encode(t1)("ab".toList)))
    assertEquals(
      "abd".toList,
      decode(frenchCode, encode(frenchCode)("abd".toList))
    )
    assertEquals(
      "huffmanestcool".toList,
      decode(frenchCode, encode(frenchCode)("huffmanestcool".toList))
    )
  }

  @Test def `decode and quickEncode a very short text should be identity`
      : Unit = new TestTrees {
    assertEquals("ab".toList, decode(t1, quickEncode(t1)("ab".toList)))
    assertEquals(
      "huffmanestcool".toList,
      decode(frenchCode, quickEncode(frenchCode)("huffmanestcool".toList))
    )
  }

  @Test def `createCodeTree(someText)' gives an optimal encoding, the number of bits when encoding 'someText' is minimal (15pts)`
      : Unit = new TestTrees {
    val tree = createCodeTree("someText".toList)
    assertEquals(
      "someText".toList,
      decode(tree, encode(tree)("someText".toList))
    )

  }
  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
