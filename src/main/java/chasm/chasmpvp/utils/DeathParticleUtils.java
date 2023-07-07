package chasm.chasmpvp.utils;

import org.bukkit.*;

public class DeathParticleUtils {

    public static void spawnSpiralParticles(Location location) {

        World world = location.getWorld();
        double radius = 1.0; // Radius of the spiral
        double height = 2.0; // Height between each spiral loop
        int particlesPerLoop = 10; // Number of particles to spawn in each loop
        double anglePerParticle = 2 * Math.PI / particlesPerLoop; // Angle between each particle

        for (double y = 0; y < height; y += 0.1) {
            for (int i = 0; i < particlesPerLoop; i++) {
                double angle = i * anglePerParticle;
                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);

                Location particleLocation = location.clone().add(x, y, z);
                world.spawnParticle(Particle.REDSTONE, particleLocation, 0, new Particle.DustOptions(Color.RED, 1));
            }
            radius += 0.1; // Increase the radius for the next spiral loop
        }
    }
}