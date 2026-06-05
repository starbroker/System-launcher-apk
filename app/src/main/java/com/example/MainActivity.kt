package com.example

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.togetherWith
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.delay
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.foundation.lazy.LazyRow
import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProviderInfo
import android.appwidget.AppWidgetHostView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.DragIndicator
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.foundation.combinedClickable
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import kotlinx.coroutines.flow.map
import kotlin.math.roundToInt
import java.util.Locale

enum class MockWidgetType { SMALL, WIDE }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WidgetsBottomSheet(apps: List<AppModel>, onDismiss: () -> Unit, onAddWidget: (AppModel, MockWidgetType) -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = Color(0xFFE8F5E9) // Light greenish tint matching references
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Widgets",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.Black)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn(
                contentPadding = PaddingValues(bottom = 48.dp)
            ) {
                items(apps) { app ->
                    AppWidgetRow(app, onAddWidget = { type ->
                        onDismiss()
                        onAddWidget(app, type)
                    })
                }
            }
        }
    }
}

@Composable
fun RealWidgetCard(appWidgetId: Int, spanX: Int = 2, spanY: Int = 2, modifier: Modifier = Modifier, isEditMode: Boolean = false, onRemove: (() -> Unit)? = null, onResize: ((Int) -> Unit)? = null) {
    val appWidgetHost = LocalAppWidgetHost.current
    val appWidgetManager = LocalAppWidgetManager.current
    var hostView by remember { mutableStateOf<AppWidgetHostView?>(null) }
    val context = LocalContext.current

    DisposableEffect(appWidgetId) {
        val appWidgetInfo = appWidgetManager.getAppWidgetInfo(appWidgetId)
        if (appWidgetInfo != null) {
            val view = appWidgetHost.createView(context, appWidgetId, appWidgetInfo)
            hostView = view
        }
        onDispose {
            hostView = null
        }
    }

    Box(modifier = modifier.fillMaxWidth().height(80.dp * spanY)) {
        if (hostView != null) {
             AndroidView(
                factory = { hostView!! },
                modifier = Modifier.fillMaxSize()
            )
        } else {
             Box(modifier = Modifier.fillMaxSize().background(Color.Gray), contentAlignment = Alignment.Center) {
                 Text("Loading widget...", color = Color.White)
             }
        }
        if (isEditMode) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f))) // Overlay to swallow touches
            if (onRemove != null) {
                androidx.compose.material3.IconButton(
                    onClick = onRemove,
                    modifier = Modifier.align(Alignment.TopEnd).background(Color.Red, CircleShape).size(24.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Remove Widget", tint = Color.White, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}

@Composable
fun MockWidgetCard(app: AppModel, type: MockWidgetType, spanX: Int = if (type == MockWidgetType.WIDE) 2 else 1, modifier: Modifier = Modifier, isEditMode: Boolean = false, onRemove: (() -> Unit)? = null, onResize: ((Int) -> Unit)? = null) {
    val width = 80.dp * spanX
    val height = 80.dp
    
    Box(
        modifier = modifier
            .widthIn(min = 80.dp)
            .fillMaxWidth()
            .height(height)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF80CBC4).copy(alpha = 0.5f)) // Mint green tint
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = if (spanX == 1) Alignment.CenterHorizontally else Alignment.Start
            ) {
                if (spanX == 1) {
                    Box(modifier = Modifier.size(24.dp).align(Alignment.CenterHorizontally)) {
                        val bitmap = remember(app.icon) {
                            try {
                                app.icon?.toBitmap(width = 48, height = 48)?.asImageBitmap()
                            } catch (e: Exception) { null }
                        }
                        if (bitmap != null) Image(bitmap = bitmap, contentDescription = null, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp)))
                    }
                    Text(app.label, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                } else {
                     Row(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = Arrangement.SpaceBetween,
                         verticalAlignment = Alignment.CenterVertically
                     ) {
                         Text(app.label, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                         Box(modifier = Modifier.size(24.dp)) {
                             val bitmap = remember(app.icon) {
                                 try {
                                     app.icon?.toBitmap(width = 48, height = 48)?.asImageBitmap()
                                 } catch (e: Exception) { null }
                             }
                             if (bitmap != null) Image(bitmap = bitmap, contentDescription = null, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp)))
                         }
                     }
                }
            }
        }
        
        if (isEditMode) {
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = "Remove",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(2.dp)
                    .clickable { onRemove?.invoke() }
            )
            
            // Resize Handle
            var currentSpanX by remember { mutableStateOf(spanX) }
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.8f))
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragEnd = {
                                onResize?.invoke(currentSpanX)
                            }
                        ) { change, dragAmount ->
                            change.consume()
                            val widthChange = dragAmount.x
                            val spanChange = (widthChange / 80.dp.toPx()).roundToInt()
                            // Smooth grid snap
                            if (spanChange != 0) {
                               val newSpan = (spanX + spanChange).coerceIn(1, 4) // max 4 columns
                               currentSpanX = newSpan
                               // We call onResize directly during drag for smooth reflow
                               onResize?.invoke(newSpan)
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.DragIndicator, contentDescription = "Resize", tint = Color.Gray, modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun AppWidgetRow(app: AppModel, onAddWidget: (MockWidgetType) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                val bitmap = remember(app.icon) {
                    try {
                        app.icon?.toBitmap(width = 48, height = 48)?.asImageBitmap()
                    } catch (e: Exception) { null }
                }
                if (bitmap != null) Image(bitmap = bitmap, contentDescription = null, modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(12.dp)))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(app.label, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Text("1 widget", color = Color.Gray, fontSize = 14.sp)
            }
        }
        
        AnimatedVisibility(visible = expanded) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                item { MockWidgetCard(app, MockWidgetType.SMALL, modifier = Modifier.clickable { onAddWidget(MockWidgetType.SMALL) }) }
                item { MockWidgetCard(app, MockWidgetType.WIDE, modifier = Modifier.clickable { onAddWidget(MockWidgetType.WIDE) }) }
            }
        }
    }
}

data class AppModel(
    val label: String,
    val packageName: String,
    val activityName: String,
    val icon: Drawable?,
    val dominantColor: Color = Color.Transparent
)

sealed class DesktopItem {
    data class App(val appModel: AppModel) : DesktopItem()
    data class Folder(val name: String, val apps: List<AppModel>) : DesktopItem()
    data class Widget(val appModel: AppModel, val type: MockWidgetType, val spanX: Int = if (type == MockWidgetType.WIDE) 2 else 1) : DesktopItem()
    data class RealWidget(val appWidgetId: Int, val spanX: Int = 2, val spanY: Int = 2) : DesktopItem()
}

val LocalAppWidgetManager = staticCompositionLocalOf<AppWidgetManager> { error("No AppWidgetManager provided") }
val LocalAppWidgetHost = staticCompositionLocalOf<AppWidgetHost> { error("No AppWidgetHost provided") }

class LauncherViewModel : ViewModel() {
    private val _apps = MutableStateFlow<List<AppModel>>(emptyList())
    val apps: StateFlow<List<AppModel>> = _apps.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val filteredApps = combine(_apps, _searchQuery) { appList, query ->
        if (query.isBlank()) {
            appList
        } else {
            appList.filter { it.label.contains(query, ignoreCase = true) }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _dockApps = MutableStateFlow<List<AppModel>>(emptyList())
    val dockApps: StateFlow<List<AppModel>> = _dockApps.asStateFlow()

    private val _gridColumns = MutableStateFlow(4)
    val gridColumns: StateFlow<Int> = _gridColumns.asStateFlow()

    fun updateGridColumns(cols: Int) {
        _gridColumns.value = cols
    }

    private val _homeDesktopItems = MutableStateFlow<List<DesktopItem>>(emptyList())
    val homeDesktopItems: StateFlow<List<DesktopItem>> = _homeDesktopItems.asStateFlow()

    private val _openFolder = MutableStateFlow<DesktopItem.Folder?>(null)
    val openFolder: StateFlow<DesktopItem.Folder?> = _openFolder.asStateFlow()

    fun openFolder(folder: DesktopItem.Folder?) {
        _openFolder.value = folder
    }

    fun addAppToHome(app: AppModel) {
        val items = _homeDesktopItems.value.toMutableList()
        items.add(DesktopItem.App(app))
        _homeDesktopItems.value = items
    }

    fun renameFolder(folder: DesktopItem.Folder, newName: String) {
        val items = _homeDesktopItems.value.toMutableList()
        val index = items.indexOf(folder)
        if (index != -1) {
            val updated = folder.copy(name = newName)
            items[index] = updated
            _homeDesktopItems.value = items
            _openFolder.value = updated
        }
    }

    fun removeDesktopItem(item: DesktopItem) {
        val items = _homeDesktopItems.value.toMutableList()
        items.remove(item)
        _homeDesktopItems.value = items
    }

    fun removeFolder(folder: DesktopItem.Folder) {
        val items = _homeDesktopItems.value.toMutableList()
        items.remove(folder)
        _homeDesktopItems.value = items
        _openFolder.value = null
    }

    fun removeAppFromFolder(folder: DesktopItem.Folder, app: AppModel) {
        val items = _homeDesktopItems.value.toMutableList()
        val index = items.indexOf(folder)
        if (index != -1) {
            val newApps = folder.apps.filter { it.packageName != app.packageName || it.activityName != app.activityName }
            if (newApps.size <= 1) {
                if (newApps.isNotEmpty()) {
                    items[index] = DesktopItem.App(newApps[0])
                } else {
                    items.removeAt(index)
                }
                _openFolder.value = null
            } else {
                val updated = folder.copy(apps = newApps)
                items[index] = updated
                _openFolder.value = updated
            }
            _homeDesktopItems.value = items
        }
    }

    fun addWidget(app: AppModel, type: MockWidgetType) {
        val items = _homeDesktopItems.value.toMutableList()
        items.add(DesktopItem.Widget(app, type))
        _homeDesktopItems.value = items
    }

    fun addRealWidget(appWidgetId: Int) {
        val items = _homeDesktopItems.value.toMutableList()
        items.add(DesktopItem.RealWidget(appWidgetId))
        _homeDesktopItems.value = items
    }

    fun updateWidgetSpan(widget: DesktopItem.Widget, newSpanX: Int) {
        val items = _homeDesktopItems.value.toMutableList()
        val index = items.indexOfFirst { it == widget }
        if (index != -1) {
            items[index] = widget.copy(spanX = newSpanX)
            _homeDesktopItems.value = items
        }
    }

    fun loadApps(packageManager: PackageManager) {
        viewModelScope.launch(Dispatchers.IO) {
            val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
            }
            val pkgAppsList: List<ResolveInfo> = packageManager.queryIntentActivities(mainIntent, 0)
            
            val appModels = pkgAppsList.map { resolveInfo ->
                val label = resolveInfo.loadLabel(packageManager).toString()
                val packageName = resolveInfo.activityInfo.packageName
                val activityName = resolveInfo.activityInfo.name
                val icon = resolveInfo.loadIcon(packageManager)
                val dominantColor = extractDominantColorGroup(icon)
                AppModel(label, packageName, activityName, icon, dominantColor)
            }.sortedBy { it.label.lowercase(Locale.getDefault()) }

            withContext(Dispatchers.Main) {
                _apps.value = appModels
                _dockApps.value = appModels.take(4)
                _homeDesktopItems.value = appModels.drop(4).take(20).map { DesktopItem.App(it) }
            }
        }
    }

    private fun extractDominantColorGroup(drawable: Drawable?): Color {
        if (drawable == null) return Color.Transparent
        return try {
            val bitmap = drawable.toBitmap(width = 24, height = 24)
            val colors = mutableMapOf<Int, Int>()
            for (x in 0 until 24 step 4) {
                for (y in 0 until 24 step 4) {
                    val pixel = bitmap.getPixel(x, y)
                    if (android.graphics.Color.alpha(pixel) > 200) {
                        val r = android.graphics.Color.red(pixel)
                        val g = android.graphics.Color.green(pixel)
                        val b = android.graphics.Color.blue(pixel)
                        
                        val colorGroup = when {
                            r > 200 && g > 200 && b > 200 -> Color.White
                            r < 80 && g < 80 && b < 80 -> Color.DarkGray
                            r > g * 1.5 && r > b * 1.5 -> Color.Red
                            g > r * 1.2 && g > b * 1.2 -> Color.Green
                            b > r * 1.2 && b > g * 1.2 -> Color(0xFF03A9F4) // Light Blue
                            r > 180 && g > 180 && b < 100 -> Color.Yellow
                            else -> Color.Transparent
                        }
                        if (colorGroup != Color.Transparent) {
                            colors[colorGroup.hashCode()] = colors.getOrDefault(colorGroup.hashCode(), 0) + 1
                        }
                    }
                }
            }
            val dominantHash = colors.maxByOrNull { it.value }?.key
            when (dominantHash) {
                Color.White.hashCode() -> Color.White
                Color.DarkGray.hashCode() -> Color.DarkGray
                Color.Red.hashCode() -> Color.Red
                Color.Green.hashCode() -> Color.Green
                Color(0xFF03A9F4).hashCode() -> Color(0xFF03A9F4)
                Color.Yellow.hashCode() -> Color.Yellow
                else -> Color.Transparent
            }
        } catch (e: Exception) {
            Color.Transparent
        }
    }

    fun moveDesktopItem(fromIndex: Int, toIndex: Int) {
        val items = _homeDesktopItems.value.toMutableList()
        if (fromIndex !in items.indices || toIndex !in items.indices) return
        val item = items.removeAt(fromIndex)
        items.add(toIndex, item)
        _homeDesktopItems.value = items
    }

    fun combineItems(fromIndex: Int, toIndex: Int) {
        val items = _homeDesktopItems.value.toMutableList()
        if (fromIndex !in items.indices || toIndex !in items.indices) return
        val fromItem = items[fromIndex]
        val toItem = items[toIndex]
        
        if (fromItem is DesktopItem.App && toItem is DesktopItem.App) {
            val cat1 = getCategoryForApp(fromItem.appModel)
            val cat2 = getCategoryForApp(toItem.appModel)
            val newCategory = if (cat1 == cat2) cat1 else "Folder"
            items[toIndex] = DesktopItem.Folder(newCategory, listOf(toItem.appModel, fromItem.appModel))
            items.removeAt(fromIndex)
            _homeDesktopItems.value = items
        } else if (fromItem is DesktopItem.App && toItem is DesktopItem.Folder) {
            items[toIndex] = toItem.copy(apps = toItem.apps + fromItem.appModel)
            items.removeAt(fromIndex)
            _homeDesktopItems.value = items
        } else if (fromItem is DesktopItem.Folder && toItem is DesktopItem.App) {
            items[toIndex] = fromItem.copy(apps = fromItem.apps + toItem.appModel)
            items.removeAt(fromIndex)
            _homeDesktopItems.value = items
        }
    }

    private fun getCategoryForApp(app: AppModel): String {
        val name = app.label.lowercase()
        return when {
            name.contains("calc") || name.contains("clock") || name.contains("weather") || name.contains("settings") || name.contains("tools") -> "Tools"
            name.contains("facebook") || name.contains("instagram") || name.contains("discord") || name.contains("whatsapp") || name.contains("chat") || name.contains("message") || name.contains("telegram") -> "Social"
            name.contains("photo") || name.contains("camera") || name.contains("gallery") || name.contains("lens") -> "Photography"
            name.contains("music") || name.contains("youtube") || name.contains("spotify") || name.contains("video") -> "Entertainment"
            name.contains("game") || name.contains("play") -> "Games"
            name.contains("shop") || name.contains("amazon") || name.contains("store") -> "Shopping"
            else -> "Productivity"
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}

class MainActivity : ComponentActivity() {
    private fun isDefaultLauncher(): Boolean {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        val resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return resolveInfo?.activityInfo?.packageName == packageName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = androidx.activity.SystemBarStyle.auto(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT),
            navigationBarStyle = androidx.activity.SystemBarStyle.auto(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT)
        )
        setContent {
            MyApplicationTheme {
                val viewModel: LauncherViewModel = viewModel()
                var showDefaultLauncherPopup by remember { mutableStateOf(false) }

                val context = LocalContext.current
                val appWidgetManager = remember { AppWidgetManager.getInstance(context) }
                val appWidgetHost = remember { AppWidgetHost(context, 1024) }
                
                DisposableEffect(appWidgetHost) {
                    appWidgetHost.startListening()
                    onDispose { appWidgetHost.stopListening() }
                }

                val widgetLauncher = androidx.activity.compose.rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    if (result.resultCode == android.app.Activity.RESULT_OK) {
                        val appWidgetId = result.data?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1) ?: -1
                        if (appWidgetId != -1) {
                            viewModel.addRealWidget(appWidgetId)
                        }
                    } else {
                        val appWidgetId = result.data?.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1) ?: -1
                        if (appWidgetId != -1) {
                            appWidgetHost.deleteAppWidgetId(appWidgetId)
                        }
                    }
                }
                
                val onAddRealWidget = {
                    val appWidgetId = appWidgetHost.allocateAppWidgetId()
                    val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_PICK).apply {
                        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                    }
                    widgetLauncher.launch(intent)
                }

                androidx.compose.runtime.CompositionLocalProvider(
                    LocalAppWidgetManager provides appWidgetManager,
                    LocalAppWidgetHost provides appWidgetHost
                ) {
                    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
                DisposableEffect(lifecycleOwner) {
                    val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
                        if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                            if (!isDefaultLauncher()) {
                                showDefaultLauncherPopup = true
                            }
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                LaunchedEffect(Unit) {
                    viewModel.loadApps(packageManager)
                }

                if (showDefaultLauncherPopup) {
                    androidx.compose.material3.AlertDialog(
                        onDismissRequest = { showDefaultLauncherPopup = false },
                        title = { Text("Set as Default") },
                        text = { Text("Would you like to set this as your default launcher?") },
                        confirmButton = {
                            TextButton(onClick = {
                                showDefaultLauncherPopup = false
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                                    val roleManager = getSystemService(android.app.role.RoleManager::class.java)
                                    if (roleManager?.isRoleAvailable(android.app.role.RoleManager.ROLE_HOME) == true &&
                                        !roleManager.isRoleHeld(android.app.role.RoleManager.ROLE_HOME)) {
                                        startActivityForResult(roleManager.createRequestRoleIntent(android.app.role.RoleManager.ROLE_HOME), 1)
                                    } else {
                                        startActivity(Intent(android.provider.Settings.ACTION_HOME_SETTINGS))
                                    }
                                } else {
                                    startActivity(Intent(android.provider.Settings.ACTION_HOME_SETTINGS))
                                }
                            }) {
                                Text("Yes")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDefaultLauncherPopup = false }) {
                                Text("No")
                            }
                        }
                    )
                }

                var isDrawerOpen by remember { mutableStateOf(false) }
                var isEditMode by remember { mutableStateOf(false) }
                var isSearchOpen by remember { mutableStateOf(false) }
                val haptic = LocalHapticFeedback.current

                val openFolder by viewModel.openFolder.collectAsState()

                BackHandler(enabled = isDrawerOpen || isEditMode || isSearchOpen || openFolder != null) {
                    if (openFolder != null) viewModel.openFolder(null)
                    else if (isSearchOpen) isSearchOpen = false
                    else if (isDrawerOpen) isDrawerOpen = false
                    else if (isEditMode) isEditMode = false
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectVerticalDragGestures(
                                onVerticalDrag = { change, dragAmount ->
                                    if (!isEditMode && dragAmount < -20) {
                                        isDrawerOpen = true
                                        change.consume()
                                    } else if (!isEditMode && dragAmount > 20) {
                                        if (isDrawerOpen) {
                                            isDrawerOpen = false
                                            change.consume()
                                        } else {
                                            try {
                                                val statusBarService = context.getSystemService("statusbar")
                                                val statusBarManager = Class.forName("android.app.StatusBarManager")
                                                val expandNotificationsPanel = statusBarManager.getMethod("expandNotificationsPanel")
                                                expandNotificationsPanel.invoke(statusBarService)
                                                change.consume()
                                            } catch (e: Exception) {
                                                e.printStackTrace()
                                            }
                                        }
                                    }
                                }
                            )
                        }
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { 
                                    if (!isDrawerOpen) {
                                        isEditMode = true 
                                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    }
                                },
                                onTap = { if (isEditMode) isEditMode = false }
                            )
                        }
                ) {
                    val homeBlur by animateDpAsState(
                        targetValue = if (isDrawerOpen || isSearchOpen || openFolder != null) 32.dp else 0.dp, 
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    
                    val darkenAlpha by androidx.compose.animation.core.animateFloatAsState(
                        targetValue = if (isDrawerOpen || isSearchOpen || openFolder != null) 0.5f else 0.0f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(homeBlur)
                    ) {
                        HomeScreen(viewModel = viewModel, isEditMode = isEditMode, onSearchClick = { isSearchOpen = true; isDrawerOpen = true; viewModel.updateSearchQuery("") }, onEnterEditMode = { isEditMode = true }, modifier = Modifier.fillMaxSize())
                    }
                    
                    if (darkenAlpha > 0f) {
                        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = darkenAlpha)))
                    }

                    var showWidgetsSheet by remember { mutableStateOf(false) }
                    var showLayoutSettings by remember { mutableStateOf(false) }
                    var showHomeScreenSettings by remember { mutableStateOf(false) }

                    // Edit Mode Overlay
                    AnimatedVisibility(
                        visible = isEditMode,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        EditModeOverlay(
                            onClose = { isEditMode = false },
                            onWidgetsClick = { showWidgetsSheet = true },
                            onLayoutClick = { showLayoutSettings = true },
                            onSettingsClick = { showHomeScreenSettings = true }
                        )
                    }

                    if (showWidgetsSheet) {
                        WidgetsBottomSheet(
                            apps = viewModel.apps.collectAsState().value, 
                            onDismiss = { showWidgetsSheet = false },
                            onAddWidget = { app, type -> viewModel.addWidget(app, type) }
                        )
                    }
                    
                    if (showLayoutSettings) {
                        val currentCols by viewModel.gridColumns.collectAsState()
                        androidx.compose.material3.AlertDialog(
                            onDismissRequest = { showLayoutSettings = false },
                            title = { Text("Layout Settings") },
                            text = { 
                                Column {
                                    Text("Choose grid columns for home screen: $currentCols") 
                                    androidx.compose.material3.Slider(
                                        value = currentCols.toFloat(),
                                        onValueChange = { viewModel.updateGridColumns(it.roundToInt()) },
                                        valueRange = 3f..6f,
                                        steps = 2
                                    )
                                }
                            },
                            confirmButton = { TextButton(onClick = { showLayoutSettings = false }) { Text("OK") } }
                        )
                    }
                    
                    if (showHomeScreenSettings) {
                        androidx.compose.material3.AlertDialog(
                            onDismissRequest = { showHomeScreenSettings = false },
                            title = { Text("Home Screen Settings") },
                            text = {
                                Column {
                                    Text("Launcher configurations and options.", modifier = Modifier.padding(bottom = 16.dp))
                                    TextButton(onClick = {
                                        showHomeScreenSettings = false
                                        showDefaultLauncherPopup = true
                                    }) { Text("Set as Default Launcher") }
                                }
                            },
                            confirmButton = { TextButton(onClick = { showHomeScreenSettings = false }) { Text("Done") } }
                        )
                    }

                    // App Drawer Overlay
                    AnimatedVisibility(
                        visible = isDrawerOpen,
                        enter = slideInVertically(
                            initialOffsetY = { it },
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioLowBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        ) + fadeIn(animationSpec = androidx.compose.animation.core.tween(300)),
                        exit = slideOutVertically(
                            targetOffsetY = { it },
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        ) + fadeOut(animationSpec = androidx.compose.animation.core.tween(200)),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AppDrawer(
                            viewModel = viewModel,
                            isSearchOpen = isSearchOpen,
                            onSearchClick = { isSearchOpen = true },
                            onClose = { isDrawerOpen = false; isSearchOpen = false; viewModel.updateSearchQuery("") }
                        )
                    }

                    // Folder Overlay
                    AnimatedVisibility(
                        visible = openFolder != null,
                        enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(250)) + scaleIn(initialScale = 0.8f, animationSpec = spring(dampingRatio = 0.7f, stiffness = Spring.StiffnessLow)),
                        exit = fadeOut(animationSpec = androidx.compose.animation.core.tween(200)) + scaleOut(targetScale = 0.9f, animationSpec = spring(stiffness = Spring.StiffnessMedium)),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        openFolder?.let { folder ->
                            FolderOverlay(
                                folder = folder,
                                viewModel = viewModel,
                                onClose = { viewModel.openFolder(null) }
                            )
                        }
                    }
                }
                } // added missing brace for CompositionLocalProvider
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: LauncherViewModel, isEditMode: Boolean = false, onSearchClick: () -> Unit = {}, onEnterEditMode: () -> Unit = {}, modifier: Modifier = Modifier) {
    val haptic = LocalHapticFeedback.current
    val homeDesktopItems by viewModel.homeDesktopItems.collectAsState()
    val dockApps by viewModel.dockApps.collectAsState()
    val pagerState = rememberPagerState(initialPage = 1, pageCount = { 2 })
    var popupApp by remember { mutableStateOf<Triple<DesktopItem.App, androidx.compose.ui.geometry.Rect, Boolean>?>(null) }
    var lastPopupApp by remember { mutableStateOf<Triple<DesktopItem.App, androidx.compose.ui.geometry.Rect, Boolean>?>(null) }

    LaunchedEffect(popupApp) {
        if (popupApp != null) {
            lastPopupApp = popupApp
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) // Lighter haptic if available, otherwise LongPress
    }

    var showDots by remember { mutableStateOf(false) }
    LaunchedEffect(pagerState.isScrollInProgress) {
        if (pagerState.isScrollInProgress) {
            showDots = true
        } else {
            delay(2000)
            showDots = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            if (page == 0) {
                // Google Discover Page
                Box(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val context = LocalContext.current
                    LaunchedEffect(pagerState.currentPage) {
                        if (pagerState.currentPage == 0) {
                            try {
                                val intent = context.packageManager.getLaunchIntentForPackage("com.google.android.googlequicksearchbox")
                                if (intent != null) {
                                    context.startActivity(intent)
                                }
                            } catch (e: Exception) {
                                // Ignore
                            }
                            // Auto scroll back to home
                            pagerState.scrollToPage(1)
                        }
                    }
                }
            } else if (page == 1) {
                // First Page: Widgets and Apps
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    val coroutineScope = rememberCoroutineScope()
                    val gridCols by viewModel.gridColumns.collectAsState()
                    AppGrid(
                        apps = homeDesktopItems, 
                        isEditMode = isEditMode, 
                        columns = gridCols, 
                        onCombine = { from, to -> viewModel.combineItems(from, to) },
                        onMove = { from, to -> viewModel.moveDesktopItem(from, to) },
                        onFolderClick = { viewModel.openFolder(it) },
                        onAppLongPress = { app, rect -> if (!isEditMode && rect != null) popupApp = Triple(app, rect, true) },
                        onRemove = { viewModel.removeDesktopItem(it) },
                        onResizeWidget = { widget, newSpanX -> viewModel.updateWidgetSpan(widget, newSpanX) },
                        onDragEdge = { isRightEdge ->
                            coroutineScope.launch {
                                // since we only have 2 pages (0 - Google Discover, 1 - Home screen), no page 2.
                                if (!isRightEdge && pagerState.currentPage > 0) {
                                    pagerState.animateScrollToPage(0)
                                }
                            }
                        },
                        onEnterEditMode = onEnterEditMode
                    )
                }
            } else {
                // Second Page: Empty or more apps
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Swipe right for Home", color = Color.White.copy(alpha = 0.5f))
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Search Button / Pagination Dots Pill
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(32.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.25f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onSearchClick() }
                .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessLow))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .blur(16.dp) // Blur effect underneath the text to simulate glass
            )
            Crossfade(
                targetState = showDots,
                animationSpec = androidx.compose.animation.core.tween(300),
                label = "search_dots_crossfade"
            ) { state ->
                if (state) {
                    // Pagination Dots
                    Row(
                        modifier = Modifier.height(32.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color = if (pagerState.currentPage == iteration) Color.White else Color.White.copy(alpha = 0.4f)
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(6.dp)
                            )
                        }
                    }
                } else {
                    // Search Pill
                    Row(
                        modifier = Modifier.height(32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White.copy(alpha = 0.8f), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Search", color = Color.White.copy(alpha = 0.8f), fontSize = 14.sp)
                    }
                }
            }
        }

        // Dock
        androidx.compose.animation.AnimatedVisibility(
            visible = !isEditMode,
            enter = androidx.compose.animation.fadeIn() + androidx.compose.animation.slideInVertically(initialOffsetY = { it }),
            exit = androidx.compose.animation.fadeOut() + androidx.compose.animation.slideOutVertically(targetOffsetY = { it }),
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        ) {
            androidx.compose.foundation.layout.BoxWithConstraints(
                contentAlignment = Alignment.Center
            ) {
                val isTablet = maxWidth > 600.dp
                val dockModifier = if (isTablet) {
                    Modifier.padding(vertical = 16.dp).clip(RoundedCornerShape(28.dp)).animateContentSize()
                } else {
                    Modifier.padding(horizontal = 16.dp, vertical = 16.dp).fillMaxWidth().clip(RoundedCornerShape(28.dp)).animateContentSize()
                }
                
                val rowModifier = if (isTablet) {
                    Modifier.animateContentSize().padding(horizontal = 24.dp, vertical = 12.dp)
                } else {
                    Modifier.animateContentSize().fillMaxWidth().padding(horizontal = 12.dp, vertical = 16.dp)
                }
                
                val arrangement = if (isTablet) {
                    Arrangement.spacedBy(20.dp)
                } else {
                    Arrangement.SpaceAround
                }

                Box(modifier = dockModifier) {
                    Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.4f)).blur(100.dp))
                    Row(
                        modifier = rowModifier,
                        horizontalArrangement = arrangement,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isTablet) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = arrangement) {
                                dockApps.forEach { app ->
                                    AppIcon(app = app, showLabel = false, isEditMode = isEditMode, onLongPress = { bounds -> if (!isEditMode && bounds != null) popupApp = Triple(DesktopItem.App(app), bounds, false) })
                                }
                            }
                            // Vertical Divider
                            Box(
                                modifier = Modifier
                                    .height(48.dp)
                                    .padding(horizontal = 8.dp)
                                    .width(2.dp)
                                    .clip(RoundedCornerShape(1.dp))
                                    .background(Color.White.copy(alpha = 0.3f))
                            )
                            // Recent Apps
                            val recentApps = viewModel.apps.collectAsState().value.reversed().take(3)
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                recentApps.forEach { app ->
                                    AppIcon(app = app, showLabel = false, isEditMode = isEditMode, onLongPress = { bounds -> if (!isEditMode && bounds != null) popupApp = Triple(DesktopItem.App(app), bounds, false) })
                                }
                            }
                        } else {
                            // Align with the 4 columns of the AppGrid
                            for (i in 0 until 4) {
                                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                    if (i < dockApps.size) {
                                        AppIcon(app = dockApps[i], showLabel = false, isEditMode = isEditMode, onLongPress = { bounds -> if (!isEditMode && bounds != null) popupApp = Triple(DesktopItem.App(dockApps[i]), bounds, false) })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    val appData = popupApp ?: lastPopupApp
    
    androidx.compose.animation.AnimatedVisibility(
        visible = popupApp != null,
        enter = androidx.compose.animation.fadeIn(animationSpec = androidx.compose.animation.core.tween(200)),
        exit = androidx.compose.animation.fadeOut(animationSpec = androidx.compose.animation.core.tween(200)),
        modifier = Modifier.fillMaxSize()
    ) {
        if (appData != null) {
            val (app, bounds, showLabel) = appData
            AppContextMenu(
                app = app.appModel,
                bounds = bounds,
                showLabel = showLabel,
                onDismiss = { popupApp = null },
                onRemove = {
                    viewModel.removeDesktopItem(app)
                    popupApp = null
                }
            )
        }
    }
}

@OptIn(androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun androidx.compose.animation.AnimatedVisibilityScope.AppContextMenu(
    app: AppModel,
    bounds: androidx.compose.ui.geometry.Rect?,
    showLabel: Boolean,
    isDrawer: Boolean = false,
    onDismiss: () -> Unit,
    onRemove: () -> Unit,
    onAddToHome: () -> Unit = {}
) {
    if (bounds == null) return
    val density = androidx.compose.ui.platform.LocalDensity.current
    val screenHeightPx = with(density) { androidx.compose.ui.platform.LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val menuHeightPx = with(density) { 210.dp.toPx() } // Approx height of the menu
    
    val isBottomHalf = bounds.center.y > screenHeightPx / 2
    val offsetY = if (isBottomHalf) bounds.top - menuHeightPx - 32f else bounds.bottom + 32f
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures { onDismiss() } }
    ) {
        // Blurred background overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
                .blur(32.dp)
        )
        // Draw the app icon again
        Box(
            modifier = Modifier
                .offset { IntOffset(bounds.left.toInt(), bounds.top.toInt()) }
                .size(width = with(density) { bounds.width.toDp() }, 
                      height = with(density) { bounds.height.toDp() })
                .animateEnterExit(
                    enter = androidx.compose.animation.scaleIn(initialScale = 0.9f, animationSpec = androidx.compose.animation.core.spring(dampingRatio = 0.6f, stiffness = 800f)),
                    exit = androidx.compose.animation.scaleOut(targetScale = 0.9f)
                )
        ) {
            AppIcon(app = app, showLabel = showLabel, isEditMode = false)
        }
        
        // Draw the menu
        Box(
            modifier = Modifier
                .offset { IntOffset(bounds.left.toInt(), offsetY.toInt()) }
                .width(240.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .animateEnterExit(
                    enter = androidx.compose.animation.scaleIn(
                        initialScale = 0.8f,
                        transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.1f, if (isBottomHalf) 1f else 0f),
                        animationSpec = androidx.compose.animation.core.spring(dampingRatio = 0.8f, stiffness = 400f)
                    ) + androidx.compose.animation.fadeIn(),
                    exit = androidx.compose.animation.scaleOut(
                        targetScale = 0.8f,
                        transformOrigin = androidx.compose.ui.graphics.TransformOrigin(0.1f, if (isBottomHalf) 1f else 0f)
                    ) + androidx.compose.animation.fadeOut()
                )
        ) {
            val context = androidx.compose.ui.platform.LocalContext.current
            Column {
                if (isDrawer) {
                    ContextMenuItem(
                        icon = Icons.Default.Add,
                        text = "Add to Home screen",
                        onClick = {
                            onAddToHome()
                            onDismiss()
                        }
                    )
                    androidx.compose.material3.HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.padding(start = 48.dp))
                    ContextMenuItem(
                        icon = Icons.Default.Info,
                        text = "App info",
                        onClick = {
                            try {
                                val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = android.net.Uri.parse("package:${app.packageName}")
                                }
                                context.startActivity(intent)
                            } catch (e: Exception) { e.printStackTrace() }
                            onDismiss()
                        }
                    )
                    androidx.compose.material3.HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.padding(start = 48.dp))
                    ContextMenuItem(
                        icon = Icons.Default.Delete,
                        text = "Uninstall",
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_DELETE, android.net.Uri.parse("package:${app.packageName}"))
                                context.startActivity(intent)
                            } catch (e: Exception) { e.printStackTrace() }
                            onDismiss()
                        }
                    )
                } else {
                    ContextMenuItem(
                        icon = Icons.Default.Widgets,
                        text = "Widgets",
                        trailingText = "6",
                        onClick = onDismiss
                    )
                    androidx.compose.material3.HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.padding(start = 48.dp))
                    ContextMenuItem(
                        icon = Icons.Default.Close,
                        text = "Remove",
                        onClick = onRemove
                    )
                    androidx.compose.material3.HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f), thickness = 1.dp, modifier = Modifier.padding(start = 48.dp))
                    ContextMenuItem(
                        icon = Icons.Default.Info,
                        text = "App info",
                        onClick = {
                            try {
                                val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                    data = android.net.Uri.parse("package:${app.packageName}")
                                }
                                context.startActivity(intent)
                            } catch (e: Exception) { e.printStackTrace() }
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ContextMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    trailingText: String? = null,
    trailingIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = Color.DarkGray, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = Color.Black, fontSize = 16.sp, modifier = Modifier.weight(1f))
        if (trailingText != null) {
            Text(text = trailingText, color = Color.Gray, fontSize = 14.sp)
        }
        if (trailingIcon != null) {
            Icon(imageVector = trailingIcon, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(20.dp))
        }
    }
}

@OptIn(androidx.compose.foundation.layout.ExperimentalLayoutApi::class)
@Composable
fun AppGrid(
    apps: List<DesktopItem>, 
    isEditMode: Boolean = false, 
    columns: Int = 4,
    onCombine: (Int, Int) -> Unit = {_,_->}, 
    onMove: (Int, Int) -> Unit = {_,_->},
    onFolderClick: (DesktopItem.Folder) -> Unit = {},
    onAppLongPress: (DesktopItem.App, Rect?) -> Unit = { _, _ -> },
    onRemove: (DesktopItem) -> Unit = {},
    onResizeWidget: (DesktopItem.Widget, Int) -> Unit = { _, _ -> },
    onDragEdge: (Boolean) -> Unit = {},
    onEnterEditMode: () -> Unit = {}
) {
    val haptic = LocalHapticFeedback.current
    androidx.compose.foundation.layout.BoxWithConstraints(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        val maxGridWidth = if (maxWidth > 800.dp) 800.dp else maxWidth
        // force layout columns based on parameter
        val rows = (apps.size + columns - 1) / columns
        val maxGridWidthPx = with(androidx.compose.ui.platform.LocalDensity.current) { maxGridWidth.toPx() }

        var draggedIndex by remember { mutableStateOf<Int?>(null) }
        var dragOffset by remember { mutableStateOf(Offset.Zero) }
        val itemBounds = remember { mutableMapOf<Int, Rect>() }

        Column(modifier = Modifier.width(maxGridWidth).padding(horizontal = 16.dp)) {
            androidx.compose.foundation.layout.FlowRow(
                modifier = Modifier.fillMaxWidth().animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMediumLow)),
                horizontalArrangement = Arrangement.Start,
                verticalArrangement = Arrangement.spacedBy(24.dp),
                maxItemsInEachRow = columns
            ) {
                for (index in apps.indices) {
                    val currentApp = apps[index]
                    val span = when (currentApp) {
                        is DesktopItem.Widget -> currentApp.spanX
                        else -> 1
                    }
                    val boundedSpan = minOf(span, columns)

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(fraction = boundedSpan.toFloat() / columns)
                            .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
                            .onGloballyPositioned { coords ->
                                    if (draggedIndex != index) {
                                        itemBounds[index] = coords.boundsInWindow()
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            val isDragged = draggedIndex == index
                            val zIndex = if (isDragged) 1f else 0f
                            val animatedOffset by androidx.compose.animation.core.animateOffsetAsState(
                                targetValue = if (isDragged) dragOffset else Offset.Zero,
                                label = "drag_offset",
                                animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
                            )
                            Box(
                                modifier = Modifier
                                    .zIndex(zIndex)
                                    .offset {
                                        IntOffset(animatedOffset.x.roundToInt(), animatedOffset.y.roundToInt())
                                    }
                                    .pointerInput(Unit) {
                                        detectDragGesturesAfterLongPress(
                                            onDragStart = { 
                                                draggedIndex = index
                                                dragOffset = Offset.Zero
                                                if (!isEditMode) {
                                                    onEnterEditMode()
                                                }
                                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                            },
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                dragOffset += dragAmount
                                                val startBounds = itemBounds[index]
                                                if (startBounds != null) {
                                                    val currentX = startBounds.center.x + dragOffset.x
                                                    if (currentX < 150f) {
                                                        onDragEdge(false) // Left
                                                    } else if (currentX > maxGridWidthPx - 150f) {
                                                        onDragEdge(true) // Right
                                                    }
                                                }
                                            },
                                                onDragEnd = {
                                                    val startBounds = itemBounds[index]
                                                    if (startBounds != null) {
                                                        val finalCenter = startBounds.center + dragOffset
                                                        var targetIndex: Int? = null
                                                        for ((idx, bounds) in itemBounds) {
                                                            if (idx != index && bounds.contains(finalCenter)) {
                                                                targetIndex = idx
                                                                break
                                                            }
                                                        }
                                                        if (targetIndex != null) {
                                                            val targetBounds = itemBounds[targetIndex]!!
                                                            val distanceX = kotlin.math.abs(finalCenter.x - targetBounds.center.x)
                                                            val distanceY = kotlin.math.abs(finalCenter.y - targetBounds.center.y)
                                                            if (distanceX < targetBounds.width / 4 && distanceY < targetBounds.height / 4) {
                                                                onCombine(index, targetIndex)
                                                            } else {
                                                                onMove(index, targetIndex)
                                                            }
                                                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                                        }
                                                    }
                                                    draggedIndex = null
                                                    dragOffset = Offset.Zero
                                                },
                                                onDragCancel = {
                                                    draggedIndex = null
                                                    dragOffset = Offset.Zero
                                                }
                                            )
                                    }
                            ) {
                                when (val item = apps[index]) {
                                    is DesktopItem.App -> AppIcon(app = item.appModel, isEditMode = isEditMode, onLongPress = { bounds -> onAppLongPress(item, bounds) }, onRemove = { onRemove(item) })
                                    is DesktopItem.Folder -> FolderIcon(folder = item, isEditMode = isEditMode, onClick = { onFolderClick(item) }) // folder has onRemove in edit view later if we want
                                    is DesktopItem.Widget -> MockWidgetCard(app = item.appModel, type = item.type, spanX = item.spanX, isEditMode = isEditMode, onRemove = { onRemove(item) }, onResize = { newSpanX -> onResizeWidget(item, newSpanX) })
                                    is DesktopItem.RealWidget -> RealWidgetCard(appWidgetId = item.appWidgetId, spanX = item.spanX, spanY = item.spanY, isEditMode = isEditMode, onRemove = { onRemove(item) })
                                }
                            }
                        }
                }
            }
        }
    }
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun AppIcon(app: AppModel, showLabel: Boolean = true, isEditMode: Boolean = false, onLongPress: ((androidx.compose.ui.geometry.Rect?) -> Unit)? = null, onRemove: (() -> Unit)? = null) {
    val context = LocalContext.current
    val view = androidx.compose.ui.platform.LocalView.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    var bounds by remember { mutableStateOf<androidx.compose.ui.geometry.Rect?>(null) }
    val scale by animateDpAsState(if (isPressed) (-4).dp else 0.dp, animationSpec = spring(dampingRatio = 0.82f, stiffness = 400f))
    val haptic = LocalHapticFeedback.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .onGloballyPositioned { bounds = it.boundsInWindow() }
            .combinedClickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    if (isEditMode) return@combinedClickable
                    val intent = Intent().apply {
                        setClassName(app.packageName, app.activityName)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
                    }
                    try {
                        val bundle = if (bounds != null) {
                            android.app.ActivityOptions.makeScaleUpAnimation(
                                view,
                                bounds!!.left.toInt(),
                                bounds!!.top.toInt(),
                                bounds!!.width.toInt(),
                                bounds!!.height.toInt()
                            ).toBundle()
                        } else null
                        context.startActivity(intent, bundle)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                onLongClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onLongPress?.invoke(bounds)
                }
            )
            .scale(1f + (scale.value / 100f))
    ) {
        val bitmap = remember(app.icon) {
            app.icon?.let {
                try {
                    it.toBitmap(width = 192, height = 192).asImageBitmap()
                } catch (e: Exception) {
                    null
                }
            }
        }

        Box(modifier = Modifier.size(56.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1f + (scale.value / 100f)),
                contentAlignment = Alignment.Center
            ) {
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap,
                        contentDescription = app.label,
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp))
                    )
                } else {
                    Text(app.label.take(1), color = Color.White) // Fallback
                }
            }
            if (isEditMode) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(2.dp)
                        .clickable { onRemove?.invoke() }
                )
            }
        }

        if (showLabel) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = app.label,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }
}

@Composable
fun FolderIcon(folder: DesktopItem.Folder, showLabel: Boolean = true, isEditMode: Boolean = false, onClick: () -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateDpAsState(if (isPressed) (-4).dp else 0.dp, animationSpec = spring(dampingRatio = 0.82f, stiffness = 400f))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                if (isEditMode) return@clickable
                onClick()
            }
    ) {
        Box(modifier = Modifier.size(56.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(1f + (scale.value / 100f))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.2f))
                    .blur(radius = 16.dp)
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    val apps = folder.apps.take(4)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MiniAppIcon(apps.getOrNull(0))
                        MiniAppIcon(apps.getOrNull(1))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        MiniAppIcon(apps.getOrNull(2))
                        MiniAppIcon(apps.getOrNull(3))
                    }
                }
            }
            if (isEditMode) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(2.dp)
                        .clickable { /* Remove entire folder implemented elsewhere */ }
                )
            }
        }

        if (showLabel) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = folder.name,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }
}

@Composable
fun MiniAppIcon(app: AppModel?) {
    Box(
        modifier = Modifier
            .size(18.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(if (app == null) Color.Transparent else Color.White.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center
    ) {
        if (app != null) {
            val bitmap = remember(app.icon) {
                try {
                    app.icon?.toBitmap(width = 48, height = 48)?.asImageBitmap()
                } catch (e: Exception) {
                    null
                }
            }
            if (bitmap != null) {
                Image(
                    bitmap = bitmap,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(4.dp))
                )
            } else {
                Text(app.label.take(1), color = Color.White, fontSize = 8.sp)
            }
        }
    }
}

@Composable
fun ClockWeatherWidget(isEditMode: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Widget 1: Harmony "5" Event/System Widget
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.White)
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(Color(0xFF4CAF50))) // Green
                        Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(Color(0xFFFF9800))) // Orange
                    }
                    Text(
                        text = "5",
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0D47A1), // Dark Blue
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "7436", fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Bold) // Steps?
                        Box(modifier = Modifier.size(20.dp).clip(CircleShape).background(Color(0xFFFF5722))) 
                    }
                }
            }
            if (isEditMode) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(2.dp)
                )
            }
        }

        // Widget 2: Calendar
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.White)
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "OCT",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "22",
                        fontSize = 48.sp,
                        color = Color(0xFFE53935), // Red
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Tuesday",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            if (isEditMode) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remove",
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(2.dp)
                )
            }
        }
    }
}

@Composable
fun MiniIcon(color: Color) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun FolderOverlay(folder: DesktopItem.Folder, viewModel: LauncherViewModel, onClose: () -> Unit) {
    val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
    val keyboardController = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current
    var folderName by remember { mutableStateOf(folder.name) }
    var isFolderEditMode by remember { mutableStateOf(false) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    if (isFolderEditMode) {
                        isFolderEditMode = false
                    } else {
                        onClose()
                        viewModel.renameFolder(folder, folderName)
                    }
                }
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .widthIn(max = 400.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White.copy(alpha = 0.15f))
                .blur(32.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = { isFolderEditMode = true },
                        onTap = { if (isFolderEditMode) isFolderEditMode = false }
                    )
                }
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                androidx.compose.foundation.text.BasicTextField(
                    value = folderName,
                    onValueChange = { folderName = it },
                    textStyle = androidx.compose.ui.text.TextStyle(
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .focusRequester(focusRequester)
                )
                
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Remove Folder",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                        .clickable { viewModel.removeFolder(folder) }
                )
            }

            val columns = 4
            val rows = (folder.apps.size + columns - 1) / columns
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                for (i in 0 until rows) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (j in 0 until columns) {
                            val index = i * columns + j
                            if (index < folder.apps.size) {
                                val currentApp = folder.apps[index]
                                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                    AppIcon(
                                        app = currentApp, 
                                        isEditMode = isFolderEditMode,
                                        onRemove = { viewModel.removeAppFromFolder(folder, currentApp) }
                                    )
                                }
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchOverlay(viewModel: LauncherViewModel, onClose: () -> Unit) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val apps by viewModel.apps.collectAsState()
    val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
    val keyboardController = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClose
            )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp)
            .imePadding()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMediumLow)),
                verticalArrangement = Arrangement.Bottom
            ) {
                if (searchQuery.isNotEmpty()) {
                    val matchedApps = apps.filter { it.label.contains(searchQuery, ignoreCase = true) }.take(4)
                    if (matchedApps.isNotEmpty()) {
                        Text("My apps", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White.copy(alpha = 0.2f))
                                .padding(16.dp)
                        ) {
                            matchedApps.forEach { app ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                        .clickable { keyboardController?.hide(); /* launch app */ }
                                ) {
                                    AppIcon(app, showLabel = false)
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(app.label, color = Color.White, fontSize = 16.sp, modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    val mockDocs = listOf("Resume.pdf", "Project_Proposal.docx", "Budget_2025.xlsx").filter { it.contains(searchQuery, ignoreCase = true) }
                    val mockSettings = listOf("Display Settings", "Wi-Fi", "Bluetooth", "Battery").filter { it.contains(searchQuery, ignoreCase = true) }
                    
                    if (mockDocs.isNotEmpty() || mockSettings.isNotEmpty()) {
                        Text("System & Files", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White.copy(alpha = 0.2f))
                                .padding(16.dp)
                        ) {
                            mockDocs.forEach { doc ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp)
                                        .clickable { keyboardController?.hide() }
                                ) {
                                    Icon(Icons.Default.Widgets, contentDescription = null, tint = Color.White)
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(doc, color = Color.White, fontSize = 16.sp, modifier = Modifier.weight(1f))
                                }
                            }
                            mockSettings.forEach { setting ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp)
                                        .clickable { keyboardController?.hide() }
                                ) {
                                    Icon(Icons.Default.Settings, contentDescription = null, tint = Color.White)
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(setting, color = Color.White, fontSize = 16.sp, modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar at the bottom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp)
                .clip(RoundedCornerShape(100.dp))
        ) {
            Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.4f)).blur(64.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                placeholder = { Text("Search apps, docs, settings...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                trailingIcon = { 
                    if (searchQuery.isNotEmpty()) {
                        Icon(
                            Icons.Default.Close, 
                            contentDescription = "Clear", 
                            modifier = Modifier.clickable { viewModel.updateSearchQuery("") }
                        )
                    }
                },
                shape = RoundedCornerShape(100.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedPlaceholderColor = Color.White.copy(alpha = 0.6f),
                    unfocusedPlaceholderColor = Color.White.copy(alpha = 0.6f),
                    focusedLeadingIconColor = Color.White.copy(alpha = 0.6f),
                    unfocusedLeadingIconColor = Color.White.copy(alpha = 0.6f),
                    focusedTrailingIconColor = Color.White.copy(alpha = 0.6f)
                ),
                singleLine = true,
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
                keyboardActions = androidx.compose.foundation.text.KeyboardActions(onSearch = { keyboardController?.hide() })
            )
        }
    }
}

enum class DrawerMode { ALL, CATEGORIES }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(viewModel: LauncherViewModel, isSearchOpen: Boolean, onSearchClick: () -> Unit, onClose: () -> Unit) {
    var showColorFilters by remember { mutableStateOf(false) }
    var activeColorFilter by remember { mutableStateOf<Color?>(null) }
    val filteredApps = viewModel.filteredApps.collectAsState().value.let { apps ->
        if (activeColorFilter != null && activeColorFilter != Color.Transparent) {
            apps.filter { it.dominantColor == activeColorFilter }
        } else {
            apps
        }
    }
    val searchQuery by viewModel.searchQuery.collectAsState()
    var mode by remember { mutableStateOf(DrawerMode.ALL) }
    var expandedCategory by remember { mutableStateOf<Pair<String, List<AppModel>>?>(null) }
    var appDrawerPopup by remember { mutableStateOf<Triple<DesktopItem.App, androidx.compose.ui.geometry.Rect, Boolean>?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClose
            )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top toggle
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .align(Alignment.Center)
                ) {
                    val animatedOffset by animateDpAsState(if (mode == DrawerMode.ALL) 0.dp else 120.dp)
                    Box(
                        modifier = Modifier
                            .offset(x = animatedOffset)
                            .size(120.dp, 36.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.White)
                    )
                    Row {
                        Box(
                            modifier = Modifier
                                .size(120.dp, 36.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { mode = DrawerMode.ALL },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("All", color = if (mode == DrawerMode.ALL) Color.Black else Color.White, fontWeight = FontWeight.Medium)
                        }
                        Box(
                            modifier = Modifier
                                .size(120.dp, 36.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) { mode = DrawerMode.CATEGORIES },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Categories", color = if (mode == DrawerMode.CATEGORIES) Color.Black else Color.White, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }

            var quickNavLetter by remember { mutableStateOf<String?>(null) }
            var sideBarHeight by remember { mutableStateOf(0f) }
            var dragY by remember { mutableStateOf<Float?>(null) }
            
            LaunchedEffect(quickNavLetter, dragY) {
                if (quickNavLetter != null && dragY == null) {
                    kotlinx.coroutines.delay(2000)
                    quickNavLetter = null
                }
            }
            
            val apps by viewModel.apps.collectAsState()
            val availableLetters = remember(apps) {
                val letters = apps.mapNotNull { it.label.firstOrNull()?.uppercaseChar() }
                    .filter { it.isLetter() }.distinct().sorted().map { it.toString() }
                listOf("#") + letters
            }

            val pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = { 2 })

            LaunchedEffect(mode) {
                if (mode == DrawerMode.ALL && pagerState.currentPage != 0) {
                    pagerState.animateScrollToPage(0)
                } else if (mode == DrawerMode.CATEGORIES && pagerState.currentPage != 1) {
                    pagerState.animateScrollToPage(1)
                }
            }

            LaunchedEffect(pagerState.currentPage) {
                mode = if (pagerState.currentPage == 0) DrawerMode.ALL else DrawerMode.CATEGORIES
            }

            val appDrawerContentBlur by androidx.compose.animation.core.animateDpAsState(if (quickNavLetter != null) 24.dp else 0.dp)
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                val gridCols by viewModel.gridColumns.collectAsState()
                androidx.compose.foundation.pager.HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize().blur(appDrawerContentBlur)
                ) { page ->
                    if (page == 0) {
                        AllAppsList(filteredApps, 120.dp, columns = gridCols, onAppLongPress = { app, bounds ->
                            if (bounds != null) {
                                appDrawerPopup = Triple(DesktopItem.App(app), bounds, true)
                            }
                        })
                    } else {
                        CategoriesList(filteredApps, 120.dp, columns = gridCols, onCategoryClick = { title, categoryApps ->
                            expandedCategory = title to categoryApps
                        })
                    }
                }
                
                if (mode == DrawerMode.ALL && searchQuery.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 8.dp, bottom = 80.dp)
                            .onGloballyPositioned { sideBarHeight = it.size.height.toFloat() }
                            .pointerInput(availableLetters) {
                                awaitPointerEventScope {
                                    while (true) {
                                        val event = awaitPointerEvent()
                                        val change = event.changes.firstOrNull() ?: continue
                                        if (change.pressed && sideBarHeight > 0) {
                                            dragY = change.position.y
                                            val index = ((change.position.y / sideBarHeight) * availableLetters.size)
                                                .toInt().coerceIn(0, availableLetters.size - 1)
                                            val selected = availableLetters[index]
                                            quickNavLetter = if (selected == "#") null else selected
                                        } else if (!change.pressed) {
                                            dragY = null
                                        }
                                    }
                                }
                            },
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        availableLetters.forEachIndexed { index, letter ->
                            val itemY = (index.toFloat() / availableLetters.size) * sideBarHeight
                            val distance = dragY?.let { Math.abs(it - itemY) } ?: Float.MAX_VALUE
                            val scale = if (distance < 120f) 1f + (1f - distance / 120f) * 1.5f else 1f
                            val xOffset = if (distance < 120f) -((1f - distance / 120f) * 30f) else 0f

                            Text(
                                text = letter,
                                color = if (quickNavLetter == letter) Color.White else Color.White.copy(alpha = 0.5f),
                                fontSize = (11f * scale).sp,
                                fontWeight = if (quickNavLetter == letter) FontWeight.Bold else FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .offset(x = xOffset.dp)
                                    .padding(vertical = 2.dp, horizontal = 8.dp)
                            )
                        }
                    }
                }
                
                // Quick Nav Overlay
                androidx.compose.animation.AnimatedVisibility(
                    visible = quickNavLetter != null,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.fillMaxSize()
                ) {
                    val appsForLetter = filteredApps.filter { it.label.firstOrNull()?.uppercaseChar()?.toString() == quickNavLetter }
                    Box(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.matchParentSize().background(Color.Black.copy(alpha = 0.6f)).clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) { quickNavLetter = null })
                        
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            if (appsForLetter.isEmpty()) {
                                Text("No apps for $quickNavLetter", color = Color.White)
                            } else {
                                LazyColumn(
                                    contentPadding = PaddingValues(top = 80.dp, bottom = 100.dp, start = 16.dp, end = 32.dp),
                                    verticalArrangement = Arrangement.spacedBy(24.dp),
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(appsForLetter.chunked(4)) { chunk ->
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            for (app in chunk) {
                                                Box(
                                                    modifier = Modifier.weight(1f),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    AppIcon(app = app, showLabel = true, isEditMode = false)
                                                }
                                            }
                                            for (i in 0 until (4 - chunk.size)) {
                                                Spacer(modifier = Modifier.weight(1f))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Bottom Search Bar / Color Filters
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            androidx.compose.animation.AnimatedVisibility(
                visible = showColorFilters,
                enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(300)),
                exit = fadeOut(animationSpec = androidx.compose.animation.core.tween(300)),
                modifier = Modifier.fillMaxWidth().align(Alignment.Center)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(100.dp))
                ) {
                    Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.4f)).blur(64.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                    val apps by viewModel.apps.collectAsState()
                    val availableColors = remember(apps) {
                        apps.map { it.dominantColor }.filter { it != Color.Transparent }.distinct()
                    }
                    val colors = listOf(Color(0xFF03A9F4), Color.Red, Color.Yellow, Color.Green, Color.White, Color.DarkGray).filter { it in availableColors }
                    if (colors.isNotEmpty()) {
                        colors.forEach { color ->
                            Box(modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(if (activeColorFilter == color) 2.dp else 0.dp, if (activeColorFilter == color) Color.White else Color.Transparent, CircleShape)
                                .clickable { activeColorFilter = if (activeColorFilter == color) null else color }
                            )
                        }
                        Icon(
                            Icons.Default.Close, 
                            contentDescription = "Close Filters", 
                            tint = Color.White, 
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.2f))
                                .clickable { 
                                    showColorFilters = false
                                    activeColorFilter = null 
                                }
                        )
                    } else {
                        Text("No colors available", color = Color.White.copy(alpha = 0.6f), modifier = Modifier.padding(16.dp))
                    }
                    }
                }
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = !showColorFilters && !isSearchOpen,
                enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(300)),
                exit = fadeOut(animationSpec = androidx.compose.animation.core.tween(300)),
                modifier = Modifier.fillMaxWidth().align(Alignment.Center)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .clip(RoundedCornerShape(100.dp))
                    ) {
                        Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.4f)).blur(64.dp))
                        val focusRequester = remember { androidx.compose.ui.focus.FocusRequester() }
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { viewModel.updateSearchQuery(it) },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequester),
                            placeholder = { Text("Search apps, docs, settings...", color = Color.White.copy(alpha = 0.6f)) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White.copy(alpha = 0.6f)) },
                            trailingIcon = {
                                if (searchQuery.isNotEmpty()) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "Clear",
                                        tint = Color.White,
                                        modifier = Modifier.clickable { viewModel.updateSearchQuery("") }
                                    )
                                }
                            },
                            shape = RoundedCornerShape(100.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                cursorColor = Color.White,
                            )
                        )
                        LaunchedEffect(isSearchOpen) {
                            if (isSearchOpen) {
                                focusRequester.requestFocus()
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .clickable { showColorFilters = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.4f)).blur(64.dp))
                        Icon(
                            imageVector = Icons.Default.ColorLens,
                            contentDescription = "Color Search",
                            tint = Color.White
                        )
                    }
                }
            }
        }
        
        // Category Folder Overlay
        androidx.compose.animation.AnimatedVisibility(
            visible = expandedCategory != null,
            enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(200)),
            exit = fadeOut(animationSpec = androidx.compose.animation.core.tween(200)),
            modifier = Modifier.fillMaxSize()
        ) {
            if (expandedCategory != null) {
                CategoryFolderOverlay(
                    title = expandedCategory!!.first,
                    apps = expandedCategory!!.second,
                    onClose = { expandedCategory = null },
                    onAppLongPress = { app, bounds ->
                        if (bounds != null) {
                            appDrawerPopup = Triple(DesktopItem.App(app), bounds, true)
                        }
                    }
                )
            }
        }

        // App Context Menu Overlay
        androidx.compose.animation.AnimatedVisibility(
            visible = appDrawerPopup != null,
            enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(200)),
            exit = fadeOut(animationSpec = androidx.compose.animation.core.tween(200)),
            modifier = Modifier.fillMaxSize()
        ) {
            if (appDrawerPopup != null) {
                val (app, bounds, showLabel) = appDrawerPopup!!
                AppContextMenu(
                    app = app.appModel,
                    bounds = bounds,
                    showLabel = showLabel,
                    isDrawer = true,
                    onDismiss = { appDrawerPopup = null },
                    onRemove = { appDrawerPopup = null },
                    onAddToHome = {
                        viewModel.addAppToHome(app.appModel)
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryFolderOverlay(title: String, apps: List<AppModel>, onClose: () -> Unit, onAppLongPress: (AppModel, androidx.compose.ui.geometry.Rect?) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClose
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .widthIn(max = 400.dp)
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(32.dp))
                .background(Color.White.copy(alpha = 0.15f))
                .blur(32.dp)
                .padding(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {}
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )

            val columns = 4
            val rows = (apps.size + columns - 1) / columns
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                for (i in 0 until rows) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (j in 0 until columns) {
                            val index = i * columns + j
                            if (index < apps.size) {
                                val currentApp = apps[index]
                                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                                    AppIcon(
                                        app = currentApp, 
                                        isEditMode = false,
                                        onLongPress = { bounds -> onAppLongPress(currentApp, bounds) }
                                    )
                                }
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AllAppsList(apps: List<AppModel>, paddingBottom: Dp, columns: Int = 4, onAppLongPress: (AppModel, androidx.compose.ui.geometry.Rect?) -> Unit) {
    androidx.compose.foundation.layout.BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        val maxGridWidth = if (maxWidth > 1000.dp) 1000.dp else maxWidth
        val actualColumns = maxOf(columns, (maxGridWidth.value / (maxGridWidth.value / columns)).toInt())
        val horizontalPadding = ((maxWidth - maxGridWidth) / 2) + 16.dp
        LazyColumn(
            contentPadding = PaddingValues(bottom = paddingBottom, start = horizontalPadding, end = horizontalPadding + 24.dp), // Extra end padding for letters
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            val rows = (apps.size + columns - 1) / columns

            items(rows) { i ->
                Row(
                    modifier = Modifier.fillMaxWidth().animateContentSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (j in 0 until columns) {
                        val index = i * columns + j
                        if (index < apps.size) {
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                AppIcon(app = apps[index], onLongPress = { bounds -> onAppLongPress(apps[index], bounds) })
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategoriesList(apps: List<AppModel>, paddingBottom: Dp, columns: Int = 4, onCategoryClick: (String, List<AppModel>) -> Unit) {
    androidx.compose.foundation.layout.BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        val maxGridWidth = if (maxWidth > 1000.dp) 1000.dp else maxWidth
        val actualColumns = maxOf(columns, (maxGridWidth.value / (maxGridWidth.value / columns)).toInt())
        val horizontalPadding = ((maxWidth - maxGridWidth) / 2) + 16.dp
        LazyColumn(
            contentPadding = PaddingValues(bottom = paddingBottom, start = horizontalPadding, end = horizontalPadding),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text("Recently installed", color = Color.White, fontSize = 14.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    apps.take(columns).forEach { app ->
                         Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                             AppIcon(app, showLabel = false)
                         }
                    }
                }
            }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                CategoryGrid("Social", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                CategoryGrid("Tools", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                if (columns >= 6) {
                    CategoryGrid("Reading", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                CategoryGrid("Photography", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                CategoryGrid("Entertainment", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                if (columns >= 6) {
                    CategoryGrid("Fitness", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxWidth()) {
                CategoryGrid("Shopping", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                CategoryGrid("Games", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                if (columns >= 6) {
                    CategoryGrid("Travel", apps.shuffled(), onCategoryClick, Modifier.weight(1f))
                }
            }
        }
    }
    }
}

@Composable
fun CategoryGrid(title: String, apps: List<AppModel>, onCategoryClick: (String, List<AppModel>) -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.15f))
                .clickable { onCategoryClick(title, apps) }
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    apps.getOrNull(0)?.let { AppIcon(it, showLabel = false) } ?: Spacer(modifier = Modifier.size(64.dp))
                    apps.getOrNull(1)?.let { AppIcon(it, showLabel = false) } ?: Spacer(modifier = Modifier.size(64.dp))
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
                    apps.getOrNull(2)?.let { AppIcon(it, showLabel = false) } ?: Spacer(modifier = Modifier.size(64.dp))
                    apps.getOrNull(3)?.let { AppIcon(it, showLabel = false) } ?: Spacer(modifier = Modifier.size(64.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(title, color = Color.White, fontSize = 12.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    }
}

@Composable
fun EditModeOverlay(onClose: () -> Unit, onWidgetsClick: () -> Unit, onLayoutClick: () -> Unit, onSettingsClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) { detectTapGestures { onClose() } }
    ) {
        // Top action buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.clip(RoundedCornerShape(32.dp))) {
                Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.25f)))
                TextButton(
                    onClick = { /* organise */ },
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text("Organise", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                }
            }

            Box(modifier = Modifier.clip(RoundedCornerShape(32.dp))) {
                Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.25f)))
                TextButton(
                    onClick = onClose,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text("Done", fontWeight = FontWeight.Medium, fontSize = 16.sp)
                }
            }
        }

        // Bottom setting bar
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            EditBottomItem(icon = Icons.Default.Widgets, label = "Widgets", onClick = onWidgetsClick)
            EditBottomItem(icon = Icons.Default.Wallpaper, label = "Wallpapers & style", onClick = {
                context.startActivity(Intent(Intent.ACTION_SET_WALLPAPER))
            })
            EditBottomItem(icon = Icons.Default.GridView, label = "Layout", onClick = onLayoutClick)
            EditBottomItem(icon = Icons.Default.Settings, label = "Home screen settings", onClick = onSettingsClick)
        }
    }
}

@Composable
fun EditBottomItem(icon: ImageVector, label: String, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.matchParentSize().background(Color.White.copy(alpha = 0.25f)))
            Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(28.dp))
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            lineHeight = 16.sp,
            modifier = Modifier.size(width = 80.dp, height = 36.dp)
        )
    }
}
