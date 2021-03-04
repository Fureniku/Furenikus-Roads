package com.silvaniastudios.roads.client.render;

import java.awt.Color;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;

public class Quad {

	private Vec3d vec1;
	private Vec3d vec2;
	private Vec3d vec3;
	private Vec3d vec4;

	float u1;
	float u2;
	float u3;
	float u4;

	float v1;
	float v2;
	float v3;
	float v4;
	
	boolean flipU = false;
	boolean flipV = false;

	TextureAtlasSprite sprite;

	protected VertexFormat format;

	public Quad(Vec3d vec1, float u1, float v1, Vec3d vec2, float u2, float v2, Vec3d vec3, float u3, float v3, Vec3d vec4, float u4, float v4, TextureAtlasSprite sprite, VertexFormat format) {
		this.vec1 = vec1;
		this.vec2 = vec2;
		this.vec3 = vec3;
		this.vec4 = vec4;

		this.format = format;

		this.u1 = u1;
		this.u2 = u2;
		this.u3 = u3;
		this.u4 = u4;

		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;

		this.sprite = sprite;
	}

	public Quad(Vec3d vec1, Vec3d vec2, Vec3d vec3, Vec3d vec4, TextureAtlasSprite sprite, VertexFormat format) {
		this(vec1, (float) vec1.x*16f, (float) vec1.z*16f, 
				vec2, (float) vec2.x*16f, (float) vec2.z*16f, 
				vec3, (float) vec3.x*16f, (float) vec3.z*16f, 
				vec4, (float) vec4.x*16f, (float) vec4.z*16f,
				sprite, format);
	}
	
	public void updateUVs() {
		if (flipU) {
			u1 = 16f - (float) vec1.x*16f;
			u2 = 16f - (float) vec2.x*16f;
			u3 = 16f - (float) vec3.x*16f;
			u4 = 16f - (float) vec4.x*16f;
		} else {
			u1 = (float) vec1.x*16f;
			u2 = (float) vec2.x*16f;
			u3 = (float) vec3.x*16f;
			u4 = (float) vec4.x*16f;
		}
		
		if (flipV) {
			v1 = 16f - (float) vec1.z*16f;
			v2 = 16f - (float) vec2.z*16f;
			v3 = 16f - (float) vec3.z*16f;
			v4 = 16f - (float) vec4.z*16f;
		} else {
			v1 = (float) vec1.z*16f;
			v2 = (float) vec2.z*16f;
			v3 = (float) vec3.z*16f;
			v4 = (float) vec4.z*16f;
		}
	}
	
	public void setFlipU(boolean flip) { 
		flipU = flip;
	}
	
	public void setFlipV(boolean flip) {
		flipV = flip;
	}

	public BakedQuad createQuad(int col) {
		Vec3d normal = vec3.subtract(vec2).crossProduct(vec1.subtract(vec2)).normalize();

		UnpackedBakedQuad.Builder builder = new UnpackedBakedQuad.Builder(format);
		builder.setTexture(sprite);

		putVertex(builder, normal, vec1.x, vec1.y, vec1.z, u1, v1, sprite, col);
		putVertex(builder, normal, vec2.x, vec2.y, vec2.z, u2, v2, sprite, col);
		putVertex(builder, normal, vec3.x, vec3.y, vec3.z, u3, v3, sprite, col);
		putVertex(builder, normal, vec4.x, vec4.y, vec4.z, u4, v4, sprite, col);
		return builder.build();
	}

	//Direct from mcjty's tutorial on IModel usage https://wiki.mcjty.eu/modding/index.php?title=Render_Block_Baked_Model-1.12
	protected void putVertex(UnpackedBakedQuad.Builder builder, Vec3d normal, double x, double y, double z, float u, float v, TextureAtlasSprite sprite, int col) {
		for (int e = 0; e < format.getElementCount(); e++) {
			switch (format.getElement(e).getUsage()) {
			case POSITION:
				builder.put(e, (float)x, (float)y, (float)z, 1.0f);
				break;
			case COLOR:
				if (col == 0) {
					builder.put(e, 1.0f, 1.0f, 1.0f, 1.0f);
				} else {
					Color color = new Color(col);
					builder.put(e, color.getRed()/255.0f, color.getGreen()/255.0f, color.getBlue()/255.0f, color.getAlpha()/255.0f);
				}
				break;
			case UV:
				if (format.getElement(e).getIndex() == 0) {
					u = sprite.getInterpolatedU(u);
					v = sprite.getInterpolatedV(v);
					builder.put(e, u, v, 0f, 1f);
					break;
				}
			case NORMAL:
				builder.put(e, (float) normal.x, (float) normal.y, (float) normal.z, 0f);
				break;
			default:
				builder.put(e);
				break;
			}
		}
	}



	public static Quad rotateQuadX(Quad quadIn, int rot) {
		quadIn.vec1 = dotProductX(quadIn.vec1, rot);
		quadIn.vec2 = dotProductX(quadIn.vec2, rot);
		quadIn.vec3 = dotProductX(quadIn.vec3, rot);
		quadIn.vec4 = dotProductX(quadIn.vec4, rot);
		return quadIn;
	}

	public static Quad rotateQuadY(Quad quadIn, int rot) {
		quadIn.vec1 = dotProductY(quadIn.vec1, rot);
		quadIn.vec2 = dotProductY(quadIn.vec2, rot);
		quadIn.vec3 = dotProductY(quadIn.vec3, rot);
		quadIn.vec4 = dotProductY(quadIn.vec4, rot);
		return quadIn;
	}

	public static Quad rotateQuadZ(Quad quadIn, int rot) {
		quadIn.vec1 = dotProductZ(quadIn.vec1, rot);
		quadIn.vec2 = dotProductZ(quadIn.vec2, rot);
		quadIn.vec3 = dotProductZ(quadIn.vec3, rot);
		quadIn.vec4 = dotProductZ(quadIn.vec4, rot);
		return quadIn;
	}


	public static void printVector(Vec3d vec) {
		System.out.printf("X: %s, Y: %s, Z: %s\n", vec.x, vec.y, vec.z);
	}

	//Me, finishing my maths uni module: I hope I never see a dot product again
	//Rendering: you called?
	public static Vec3d dotProductX(Vec3d vec, int rot) {
		//[1 0    0  ] [x]
		//[0 cos -sin] [y]
		//[0 sin  cos] [z]

		vec = vec.add(new Vec3d(-0.5, -0.5, -0.5));
		
		double cos = Math.cos(Math.toRadians(rot));
		double sin = Math.sin(Math.toRadians(rot));

		double vecX = vec.x;
		double vecY = (cos * vec.y) + ((-sin) * vec.z);
		double vecZ = (sin * vec.y) + (cos    * vec.z);

		return new Vec3d(vec.x + 0.5, vecY + 0.5, vecZ + 0.5);
	}

	public static Vec3d dotProductY(Vec3d vec, int rot) {
		//[cos  0 sin] [x]
		//[0    1 0  ] [y]
		//[-sin 0 cos] [z]

		vec = vec.add(new Vec3d(-0.5, -0.5, -0.5));

		double cos = Math.cos(Math.toRadians(rot));
		double sin = Math.sin(Math.toRadians(rot));

		double vecX = (cos    * vec.x) + (sin * vec.z);
		double vecZ = ((-sin) * vec.x) + (cos * vec.z);

		return new Vec3d(vecX + 0.5, vec.y + 0.5, vecZ + 0.5);
	}

	public static Vec3d dotProductZ(Vec3d vec, int rot) {
		Vec3d vecOut = new Vec3d(0,0,0);
		//[cos -sin 0] [x]
		//[sin  cos 0] [y]
		//[0    0   1] [z]
		
		vec = vec.add(new Vec3d(-0.5, -0.5, -0.5));

		double cos = Math.cos(Math.toRadians(rot));
		double sin = Math.sin(Math.toRadians(rot));

		double vecX = (cos * vec.x) + ((-sin) * vec.y);
		double vecY = (sin * vec.x) + (cos    * vec.y);

		return new Vec3d(vecX + 0.5, vecY + 0.5, vec.z + 0.5);
	}
}
