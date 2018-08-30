package com.thezs.fabianzachs.tapattack.GameFragment;

import java.util.Random;

public class ShapePicker {

    private ShapeGetter shapeGetter;

    public ShapePicker(String shape) {
        this.shapeGetter = new SimpleShapeGetter(shape);
    }

    public ShapePicker(int probCircle, int probSquare, int probArrow, int probCross, int probStar) {
        this.shapeGetter = new CustomShapeGetter(probCircle, probSquare, probArrow, probCross, probStar);
    }

    public String getShape() {
        return shapeGetter.getShape();
    }



    public interface ShapeGetter {
        String getShape();
    }

    private class SimpleShapeGetter implements ShapeGetter {

        private String shape;

        SimpleShapeGetter(String shape) {
            this.shape = shape;
        }

        @Override
        public String getShape() {
            return shape;
        }
    }

    private class CustomShapeGetter implements ShapeGetter {
        private int probCircle, probSquare, probArrow, probCross, probStar;
        private Random random = new Random();
        private int totalPob;


        CustomShapeGetter(int probCircle, int probSquare, int probArrow, int probCross, int probStar) {
            this.probCircle = probCircle;
            this.probSquare = probSquare;
            this.probArrow = probArrow;
            this.probCross = probCross;
            this.probStar = probStar;

            totalPob = probCircle + probSquare + probArrow + probCross + probStar;
        }

        @Override
        public String getShape() {
            int i = random.nextInt(totalPob);

            if (i < probCircle)
                return "circle";
            else if (i<probCircle+probSquare)
                return "square";
            else if (i<probCircle+probSquare+probArrow)
                return "arrow";
            else if (i<probCircle+probSquare+probArrow+probCross)
                return "cross";
            else if (i<probCircle+probSquare+probArrow+probCross+probStar)
                return "star";


            throw new IllegalStateException("randomly generated number not in bounds of picking a shape");
        }
    }

}
