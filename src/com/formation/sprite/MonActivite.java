package com.formation.sprite;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class MonActivite extends BaseGameActivity {

	private Camera camera;
	
	private final static int CAMERA_LARGEUR = 480;
	private final static int CAMERA_HAUTEUR = 320;
	
	private BitmapTextureAtlas texturePersonnage;
	private BitmapTextureAtlas textureSoldier;
	private TextureRegion personnage;
	private TiledTextureRegion soldier;
	
	@Override
	public Engine onLoadEngine() {
		camera = new Camera(0, 0, CAMERA_LARGEUR, CAMERA_HAUTEUR);
		return new Engine(new EngineOptions(true, 
				ScreenOrientation.LANDSCAPE, 
				new RatioResolutionPolicy(CAMERA_LARGEUR, CAMERA_HAUTEUR), 
				camera));
	}

	@Override
	public void onLoadResources() {
		texturePersonnage = new BitmapTextureAtlas(32, 64);
		textureSoldier = new BitmapTextureAtlas(512, 64);
		
		personnage = BitmapTextureAtlasTextureRegionFactory.createFromAsset(texturePersonnage, this, "gfx/personnage.png", 0, 0);
		soldier = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(textureSoldier, this, "gfx/soldier.png", 0, 0, 8, 1);
		
		getEngine().getTextureManager().loadTextures(texturePersonnage,textureSoldier);
	}

	@Override
	public Scene onLoadScene() {
		final Sprite sprite = new Sprite(50, 50, personnage);
		final AnimatedSprite animatedSprite = new AnimatedSprite(50, 100, soldier);
		animatedSprite.animate(25, true);
		
		final Scene scene = new Scene();
		scene.attachChild(sprite);
		scene.attachChild(animatedSprite);		
		return scene;
	}

	@Override
	public void onLoadComplete() {
		
	}

}