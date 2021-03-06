package mods.natura.worldgen.retro;


import java.io.Serializable;

import net.minecraft.world.chunk.Chunk;

/**
 * Standardized implementation for representing and manipulating Chunk Coordinates. Provides standard Java Collection interaction.
 * From COFH LIB
 * @author King Lemming
 * 
 */
public final class ChunkCoord implements Comparable<ChunkCoord>, Serializable {

        public int chunkX;
        public int chunkZ;

        public ChunkCoord(Chunk chunk) {

                this.chunkX = chunk.xPosition;
                this.chunkZ = chunk.zPosition;
        }

        public ChunkCoord(BlockCoord c) {

                this(c.x >> 4, c.z >> 4);
        }

        public ChunkCoord(int x, int z) {

                this.chunkX = x;
                this.chunkZ = z;
        }

        public int getCenterX() {

                return (this.chunkX << 4) + 8;
        }

        public int getCenterZ() {

                return (this.chunkZ << 4) + 8;
        }

        public void step(int dir) {

                chunkX = BlockHelper.SIDE_COORD_MOD[dir][0];
                chunkZ = BlockHelper.SIDE_COORD_MOD[dir][2];
        }

        public void step(int dir, int dist) {

                switch (dir) {
                case 2:
                        this.chunkZ -= dist;
                        break;
                case 3:
                        this.chunkZ += dist;
                        break;
                case 4:
                        this.chunkX -= dist;
                        break;
                case 5:
                        this.chunkX += dist;
                        break;
                default:
                }
        }

        public ChunkCoord copy() {

                return new ChunkCoord(chunkX, chunkZ);
        }

        @Override
        public boolean equals(Object obj) {

                if (!(obj instanceof ChunkCoord)) {
                        return false;
                }
                ChunkCoord other = (ChunkCoord) obj;
                return this.chunkX == other.chunkX && this.chunkZ == other.chunkZ;
        }

        @Override
        public int hashCode() {

                int hash = chunkX;
                hash *= 31 + this.chunkZ;
                return hash;
        }

        @Override
        public String toString() {

                return "[" + this.chunkX + ", " + this.chunkZ + "]";
        }

        /* Comparable */
        @Override
        public int compareTo(ChunkCoord other) {

                return this.chunkX == other.chunkX ? this.chunkZ - other.chunkZ : this.chunkX - other.chunkX;
        }

}
