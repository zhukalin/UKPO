

import org.junit.Assert;
import org.junit.Test;

public class TestMain {

        @Test
        public void testInsertToEmptyTree() {
            Main t1 = new Main();
            t1.insert(1);
            Assert.assertEquals(1, t1.mRoot.mValue);
        }
    @Test
    public void testDefaultConstructor() {
        Main t1 = new Main();
        Assert.assertNull(t1.mRoot);
    }

    @Test
    public void testIntegerConstructor() {
        Main t1 = new Main(1);
        Assert.assertNotNull(t1.mRoot);
    }


    @Test
    public void testEqualTreesEqualHashCodes() {
        Main t1 = new Main(10);
        t1.insert(2, 12);
        Main t2 = new Main(10);
        t2.insert(2, 12);
        Assert.assertEquals(t1.hashCode(), t2.hashCode());
    }


    @Test
    public void testToStringEmpty() {
        Main t1 = new Main();
        Assert.assertEquals("[]", t1.toString());
    }

    @Test
    public void testToStringSingleNode() {
        Main t1 = new Main(1);
        Assert.assertEquals("[1]", t1.toString());
    }

    @Test
    public void testToStringManyNodes() {
        Main t1 = new Main(1);
        t1.insert(12, 56, 7, 2, 1);
        Assert.assertEquals("[1, 1, 2, 7, 12, 56]", t1.toString());
    }

        @org.junit.Test
        public void testSingleRotateLeft() {
            Main t1 = new Main(10);
            t1.insert(14, 56);
            Assert.assertEquals(t1.mRoot.mValue, 14);
            Assert.assertEquals(t1.mRoot.mLeft.mValue, 10);
            Assert.assertEquals(t1.mRoot.mRight.mValue, 56);
        }

        @org.junit.Test
        public void testSingleRotateRight() {
            Main t1 = new Main(10);
            t1.insert(2, 1);
            Assert.assertEquals(t1.mRoot.mValue, 2);
            Assert.assertEquals(t1.mRoot.mLeft.mValue, 1);
            Assert.assertEquals(t1.mRoot.mRight.mValue, 10);
        }
    }

