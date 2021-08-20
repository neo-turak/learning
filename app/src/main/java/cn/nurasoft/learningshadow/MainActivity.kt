package cn.nurasoft.learningshadow

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.find
import timber.log.Timber
import timber.log.Timber.DebugTree
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    // private lateinit var imageView: ImageView
     private lateinit var btn:Button
     lateinit var bitmapList:MutableList<Bitmap>
     var position=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Timber 优雅的Log打印库
        Timber.plant(DebugTree())
        setContentView(R.layout.activity_main)
        //绑定.
        bitmapList= mutableListOf()
      //  content=findViewById(R.id.main)
        btn=findViewById(R.id.btn_scale)
       // imageView=find(R.id.iv_image)
      //  iView=findViewById(R.id.iv_me)
        //点击事件.
        btn.setOnClickListener {
        //    imageView.setImageBitmap(bitmapList[position])
            if (position==bitmapList.size-1){
                position=0
            }else{
                position++
            }
        }

      //  imageView=findViewById(R.id.iv_src)


/*        Glide.with(this)
            .asBitmap()
            .load("https://cdn.modesens.cn/blog/20210710-1.png")
       //     .load("https://cdn.modesens.cn/blog/628lj1.png")
       //     .load("https://cdn.modesens.cn/blog/20210621-1.png")
            .listener(object :RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                  return  false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource!=null){
                        val w=resource.width
                        val h=resource.height
                        val list= mutableListOf<Int>()
                        var start=0
                        for (x:Int in 1 until w){
                            var yes: Boolean
                            for (y:Int in 1 until h){
                                val pixel=resource.getPixel(x,y)
                                yes = pixel.red>250 && pixel.blue>250 && pixel.green>250
                                if (y==h-1 && yes){
                                    if (start+1!=x){
                                        list.add(x)
                                    }
                                    start=x
                                }
                            }
                        }
                        bitmapList.clear()
                        if (list.isNotEmpty()){
                            for (i:Int in 0 until list.size){
                                val bitmap:Bitmap
                                if (i==0){
                                     bitmap=Bitmap.createBitmap(resource,0,0,list[0],resource.height)
                                }else if (i==list.size-1){
                                     bitmap=Bitmap.createBitmap(resource,list[i],0,resource.width-list[i],resource.height)
                                }else{
                                     bitmap=Bitmap.createBitmap(resource,list[i-1],0,list[i]-list[i-1],resource.height)
                                }
                                bitmapList.add(bitmap)
                            }

                        }
                        Timber.e("数组：$list")
                        imageView.setImageBitmap(resource)
                    }
                    return true
                }

            }).into(imageView)*/
    }

    // Generate palette synchronously and return it
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            // Use generated instance

        }
    }

    fun manipulateColor(color: Int, factor: Float): Int {
        val a = 80
        val r = (Color.red(color) * factor).roundToInt()
        val g = (Color.green(color) * factor).roundToInt()
        val b = (Color.blue(color) * factor).roundToInt()
        return Color.argb(
            a,
            r.coerceAtMost(255),
            g.coerceAtMost(255),
            b.coerceAtMost(255)
        )
    }


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent){
        Toast.makeText(this,event.message,Toast.LENGTH_SHORT).show()
    }

    @Subscribe
    public fun handleSomthingElse(event: String){
        Log.e(tag,event)
    }

    companion object{
        val tag=this.javaClass.simpleName
    }
}