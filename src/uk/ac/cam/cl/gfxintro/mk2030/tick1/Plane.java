package uk.ac.cam.cl.gfxintro.mk2030.tick1;

public class Plane extends SceneObject {

    // Plane constants
    private final double DEFAULT_PLANE_KD = 0.6;
    private final double DEFAULT_PLANE_KS = 0.0;
    private final double DEFAULT_PLANE_ALPHA = 0.0;
    private final double DEFAULT_PLANE_REFLECTIVITY = 0.1;

    // A point in the plane
    private Vector3 point;

    // The normal of the plane
    private Vector3 normal;

    public Plane(Vector3 point, Vector3 normal, ColorRGB colour) {
        this.point = point;
        this.normal = normal;
        this.colour = colour;

        this.phong_kD = DEFAULT_PLANE_KD;
        this.phong_kS = DEFAULT_PLANE_KS;
        this.phong_alpha = DEFAULT_PLANE_ALPHA;
        this.reflectivity = DEFAULT_PLANE_REFLECTIVITY;
    }

    public Plane(Vector3 point, Vector3 normal, ColorRGB colour, double kD, double kS, double alphaS, double reflectivity) {
        this.point = point;
        this.normal = normal;
        this.colour = colour;

        this.phong_kD = kD;
        this.phong_kS = kS;
        this.phong_alpha = alphaS;
        this.reflectivity = reflectivity;
    }

    // Intersect this plane with ray
    public RaycastHit intersectionWith(Ray ray) {
        // Get ray parameters
        Vector3 O = ray.getOrigin();
        Vector3 D = ray.getDirection();

        // Get plane parameters
        Vector3 R = this.point;
        Vector3 N = this.normal;

        double sTop = ((R.subtract(O)).dot(N));
        double sBottom = (D.dot(N));
        double s = sTop/sBottom;

        Vector3 pointinter = O.add(D.scale(s));

        if (s < 0) {
            return new RaycastHit();
        } else {
            return (new RaycastHit(this,s,pointinter,N));
        }


        // Calculate ray parameter s at intersection
        // If intersection occurs behind camera, return empty RaycastHit;
        //			otherwise return RaycastHit describing point of intersection
    }

    // Get normal to the plane
    public Vector3 getNormalAt(Vector3 position) {
        return normal; // normal is the same everywhere on the plane
    }
}
