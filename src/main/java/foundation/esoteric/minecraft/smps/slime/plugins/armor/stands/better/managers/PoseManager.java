package foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.managers;

import org.bukkit.entity.ArmorStand;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import foundation.esoteric.minecraft.smps.slime.plugins.armor.stands.better.BetterArmorStandsPlugin;

import java.util.ArrayList;

public class PoseManager {

    private final ArrayList<ArrayList<ArrayList<Double>>> poses;

    public ArrayList<ArrayList<ArrayList<Double>>> getPoses() {
        return poses;
    }

    public PoseManager(@NotNull BetterArmorStandsPlugin plugin) {
        poses = (ArrayList<ArrayList<ArrayList<Double>>>) plugin.getConfig().getList("pose-changing.poses");
        assert poses != null;

        for (ArrayList<ArrayList<Double>> poseRotations : poses) {
            for (ArrayList<Double> componentRotations : poseRotations) {
                componentRotations.replaceAll(Math::toRadians);
            }
        }
    }

    public int getPoseIndex(@NotNull ArmorStand armorStand) {
        EulerAngle bodyPose = armorStand.getBodyPose();
        EulerAngle headPose = armorStand.getHeadPose();
        EulerAngle leftArmPose = armorStand.getLeftArmPose();
        EulerAngle leftLegPose = armorStand.getLeftLegPose();
        EulerAngle rightArmPose = armorStand.getRightArmPose();
        EulerAngle rightLegPose = armorStand.getRightLegPose();

        ArrayList<ArrayList<Double>> rotations = new ArrayList<>();

        rotations.add(toRotationsArray(bodyPose));
        rotations.add(toRotationsArray(headPose));
        rotations.add(toRotationsArray(leftArmPose));
        rotations.add(toRotationsArray(leftLegPose));
        rotations.add(toRotationsArray(rightArmPose));
        rotations.add(toRotationsArray(rightLegPose));

        for (int i = 0; i < poses.size(); i++) {
            ArrayList<ArrayList<Double>> poseRotations = poses.get(i);

            if (poseRotations.equals(rotations)) {
                return i;
            }
        }

        return 0;
    }

    public void setPose(@NotNull ArmorStand stand, int poseIndex) {
        ArrayList<ArrayList<Double>> poseRotations = poses.get(poseIndex);

        stand.setBodyPose(toEulerAngle(poseRotations.get(0)));
        stand.setHeadPose(toEulerAngle(poseRotations.get(1)));
        stand.setLeftArmPose(toEulerAngle(poseRotations.get(2)));
        stand.setLeftLegPose(toEulerAngle(poseRotations.get(3)));
        stand.setRightArmPose(toEulerAngle(poseRotations.get(4)));
        stand.setRightLegPose(toEulerAngle(poseRotations.get(5)));
    }

    public ArrayList<Double> toRotationsArray(@NotNull EulerAngle eulerAngle) {
        ArrayList<Double> list = new ArrayList<>();

        list.add(eulerAngle.getX());
        list.add(eulerAngle.getY());
        list.add(eulerAngle.getZ());

        return list;
    }

    public EulerAngle toEulerAngle(@NotNull ArrayList<Double> rotations) {
        return new EulerAngle(rotations.get(0), rotations.get(1), rotations.get(2));
    }
}
